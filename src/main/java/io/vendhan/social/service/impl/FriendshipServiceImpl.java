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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipDao friendshipDao;

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
        return null;
    }

    @Override
    public FriendshipDto getCommonFriends(
            FriendshipDto friendshipDto) throws Exception {
        return null;
    }

    private boolean connect(
            String emailOne, String emailTwo) throws Exception {
        Person personOne = personDao.findByEmail(emailOne);
        Person personTwo = personDao.findByEmail(emailTwo);
        FriendshipId friendshipId =
                new FriendshipId(personOne.getId(), personTwo.getId());
        Optional<Friendship> existingFriendship =
                friendshipDao.getJpaRepository().findById(friendshipId);
        if(existingFriendship.isPresent()) {
            if(StatusEnum.ACTIVE.getLabel().equalsIgnoreCase(
                    existingFriendship.get().getStatus().getLabel())) {
                return true;
            } else {
                return false;
            }
        } else {
            Status status =
                    statusDao.getJpaRepository().findByLabel(
                            StatusEnum.ACTIVE.getLabel()).get(0);
            Friendship friendship = new Friendship(
                    friendshipId.getFriendOneId(),
                    friendshipId.getFriendTwoId(), status);
            friendshipDao.getJpaRepository().save(friendship);
            return true;
        }
    }
}
