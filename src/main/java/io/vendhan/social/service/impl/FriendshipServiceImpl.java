package io.vendhan.social.service.impl;

import io.vendhan.social.dao.FriendshipDao;
import io.vendhan.social.model.FriendshipDto;
import io.vendhan.social.model.PersonDto;
import io.vendhan.social.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipDao friendshipDao;

    @Override
    public boolean connect(FriendshipDto friendshipDto) {
        return false;
    }

    @Override
    public FriendshipDto getFriends(PersonDto personDto) {
        return null;
    }

    @Override
    public FriendshipDto getCommonFriends(FriendshipDto friendshipDto) {
        return null;
    }
}
