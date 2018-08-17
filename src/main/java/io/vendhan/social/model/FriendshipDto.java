package io.vendhan.social.model;

import io.vendhan.social.constant.ValidationConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FriendshipDto {

    @NotNull(message = ValidationConstant.NO_EMAIL)
    private List<@Email(message = ValidationConstant.EMAIL) String> friends;

    public FriendshipDto() {}

    public FriendshipDto(
            @NotNull(message = ValidationConstant.NO_EMAIL)
                    List<@Email(message = ValidationConstant.EMAIL) String> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
