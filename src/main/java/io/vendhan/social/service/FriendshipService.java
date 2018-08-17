package io.vendhan.social.service;

import io.vendhan.social.model.FriendshipDto;
import io.vendhan.social.model.PersonDto;
import org.springframework.stereotype.Service;

@Service
public interface FriendshipService extends BaseService {

    boolean connect(FriendshipDto friendshipDto);

    FriendshipDto getFriends(PersonDto personDto);

    FriendshipDto getCommonFriends(FriendshipDto friendshipDto);

}
