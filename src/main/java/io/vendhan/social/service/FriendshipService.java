package io.vendhan.social.service;

import io.vendhan.social.model.FriendshipDto;
import io.vendhan.social.model.PersonDto;
import org.springframework.stereotype.Service;

@Service
public interface FriendshipService extends BaseService {

    boolean connect(FriendshipDto friendshipDto) throws Exception;

    FriendshipDto getFriends(PersonDto personDto) throws Exception;

    FriendshipDto getCommonFriends(FriendshipDto friendshipDto) throws Exception;

    boolean isFriends(String personOneEmail, String personTwoEmail) throws Exception;

}
