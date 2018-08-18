package io.vendhan.social.service;

import io.vendhan.social.BaseTest;
import io.vendhan.social.dao.FriendshipDao;
import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.model.FriendshipDto;
import io.vendhan.social.model.PersonDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTest extends BaseTest {

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private FriendshipDao friendshipDao;

    @Test
    public void testConnectNonBlockedContacts() throws Exception {
        List<String> friends = new ArrayList<>();
        friends.add("andy@example.com");
        friends.add("john@example.com");
        FriendshipDto friendshipDto = new FriendshipDto(friends);
        Assert.assertTrue("Friend connection failed",
                friendshipService.connect(friendshipDto));
        Friendship friendship =
                friendshipDao.getByEmails(friends.get(0), friends.get(1));
        Assert.assertNotNull("friend one is not connected to two",
                friendship);
        friendship =
                friendshipDao.getByEmails(friends.get(1), friends.get(0));
        Assert.assertNotNull("friend two is not connected to one",
                friendship);
    }

    @Test
    public void testGetFriendsForAPersonWithOneFriend() throws Exception {
        List<String> friends = new ArrayList<>();
        friends.add("andy@example.com");
        friends.add("john@example.com");
        FriendshipDto friendshipDto = new FriendshipDto(friends);
        Assert.assertTrue("Friend connection failed",
                friendshipService.connect(friendshipDto));
        FriendshipDto friendships =
                friendshipService.getFriends(
                        new PersonDto("andy@example.com"));
        Assert.assertNotNull("Friend count incorrect", friendships);
        Assert.assertEquals("No. of friends not equal to one",
                1, friendships.getFriends().size());
        Assert.assertEquals(
                "john@example.com must be friends with andy@example.com",
                "john@example.com", friendships.getFriends().get(0));
    }

    @Test
    public void testCommonFriends() throws Exception {

        List<String> friends = new ArrayList<>();
        friends.add("andy@example.com");
        friends.add("john@example.com");
        FriendshipDto friendshipDto = new FriendshipDto(friends);
        Assert.assertTrue("Friend connection failed",
                friendshipService.connect(friendshipDto));

        friends = new ArrayList<>();
        friends.add("user1@example.com");
        friends.add("john@example.com");
        friendshipDto = new FriendshipDto(friends);
        Assert.assertTrue("Friend connection failed",
                friendshipService.connect(friendshipDto));

        List<String> commonFriendsInp = new ArrayList<>();
        commonFriendsInp.add("andy@example.com");
        commonFriendsInp.add("user1@example.com");
        FriendshipDto commonFriends = friendshipService.getCommonFriends(
                new FriendshipDto(commonFriendsInp));
        Assert.assertNotNull(
                "Common Friends count incorrect", commonFriends);
        Assert.assertEquals("No. of common friends not equal to one",
                1, commonFriends.getFriends().size());
        Assert.assertEquals(
                "john@example.com must be friends with both andy@example.com and user1@example.com",
                "john@example.com", commonFriends.getFriends().get(0));
    }

}
