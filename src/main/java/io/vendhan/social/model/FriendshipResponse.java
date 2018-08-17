package io.vendhan.social.model;

import java.util.List;

public class FriendshipResponse extends Response {
    private List<String> friends;

    private int count = 0;

    public FriendshipResponse(FriendshipDto friendshipDto) {
        this.friends = friendshipDto.getFriends();
        if(null != this.friends) {
            this.count = this.friends.size();
        }
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
