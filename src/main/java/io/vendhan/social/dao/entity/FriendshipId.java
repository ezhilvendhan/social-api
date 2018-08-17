package io.vendhan.social.dao.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FriendshipId implements Serializable {
    private long friendOneId;

    private long friendTwoId;

    public FriendshipId() {}

    public FriendshipId(long friendOneId, long friendTwoId) {
        this.friendOneId = friendOneId;
        this.friendTwoId = friendTwoId;
    }

    public long getFriendOneId() {
        return friendOneId;
    }

    public long getFriendTwoId() {
        return friendTwoId;
    }
}
