package io.vendhan.social.service.impl;

import io.vendhan.social.dao.FriendshipDao;
import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.StatusDao;
import io.vendhan.social.dao.constant.StatusEnum;
import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.entity.FriendshipId;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.entity.Status;
import io.vendhan.social.model.FriendshipDto;
import io.vendhan.social.model.PersonDto;
import io.vendhan.social.service.FriendshipService;
import io.vendhan.social.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipDao friendshipDao;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private StatusDao statusDao;

    @Override
    public boolean connect(
            FriendshipDto friendshipDto) throws Exception {
        String friendOneEmail = friendshipDto.getFriends().get(0);
        String friendTwoEmail = friendshipDto.getFriends().get(1);
        boolean status = this.connect(friendOneEmail, friendTwoEmail);
        if(status) {
            return this.connect(friendTwoEmail, friendOneEmail);
        }
        return false;
    }

    @Override
    public FriendshipDto getFriends(
            PersonDto personDto) throws Exception {
        List<Friendship> friends =
                friendshipDao.getFriendsByEmail(personDto.getEmail());
        List<String> emails;
        if(null != friends) {
            emails = friends.stream().map(
                    _friendship -> _friendship.getFriendTwo().getEmail()
            ).collect(Collectors.toList());
        } else {
            emails = new ArrayList<>();
        }
        return new FriendshipDto(emails);
    }

    @Override
    public FriendshipDto getCommonFriends(
            FriendshipDto friendshipDto) throws Exception {
        List<String> emails = friendshipDao.getCommonFriendsByEmail(
                friendshipDto.getFriends().get(0),
                friendshipDto.getFriends().get(1));
        if (null == emails) {
            emails = new ArrayList<>();
        }
        return new FriendshipDto(emails);
    }

    @Override
    public boolean isFriends(
            String personOneEmail, String personTwoEmail) throws Exception {
        Optional<Friendship> existingFriendship =
                friendshipDao.getByEmails(personOneEmail, personTwoEmail);
        return existingFriendship.isPresent();

    }

    private boolean connect(
            String emailOne, String emailTwo) throws Exception {
        Optional<Friendship> existingFriendship =
                friendshipDao.getByEmails(emailOne, emailTwo);
        if(subscriptionService.isBlocked(emailOne, emailTwo)) {
            return false;
        }
        if(existingFriendship.isPresent()) {
            if(StatusEnum.ACTIVE.getLabel().equalsIgnoreCase(
                    existingFriendship.get().getStatus().getLabel())) {
                return true;
            } else if(StatusEnum.INACTIVE.getLabel().equalsIgnoreCase(
                    existingFriendship.get().getStatus().getLabel())) {
                updateInactiveFriendStatus(existingFriendship);
                return true;
            } else {
                return false;
            }
        } else {
            saveNewFriendship(emailOne, emailTwo);
            return true;
        }
    }

    private void saveNewFriendship(String emailOne, String emailTwo) {
        Person personOne = personDao.findByEmail(emailOne);
        Person personTwo = personDao.findByEmail(emailTwo);
        FriendshipId friendshipId =
                new FriendshipId(personOne.getId(), personTwo.getId());
        Status status =
                statusDao.getJpaRepository().findByLabel(
                        StatusEnum.ACTIVE.getLabel()).get(0);
        Friendship friendship = new Friendship(
                friendshipId.getFriendOneId(),
                friendshipId.getFriendTwoId(), status);
        friendshipDao.getJpaRepository().save(friendship);
    }

    private void updateInactiveFriendStatus(Optional<Friendship> existingFriendship) {
        Status status =
                statusDao.getJpaRepository().findByLabel(
                        StatusEnum.ACTIVE.getLabel()).get(0);
        existingFriendship.get().setStatus(status);
        friendshipDao.getJpaRepository().saveAndFlush(existingFriendship.get());
    }
}
