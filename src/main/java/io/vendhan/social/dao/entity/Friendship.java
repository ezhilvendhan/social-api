package io.vendhan.social.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FRIENDSHIP")
@IdClass(FriendshipId.class)
public class Friendship implements Serializable {

    @Id
    @Column(name = "FRIEND_ONE")
    private long friendOneId;

    @Id
    @Column(name = "FRIEND_TWO")
    private long friendTwoId;

    @ManyToOne
    @JoinColumn(name = "FRIEND_ONE", insertable = false, updatable = false)
    private Person friendOne;

    @ManyToOne
    @JoinColumn(name = "FRIEND_TWO", insertable = false, updatable = false)
    private Person friendTwo;

    @ManyToOne
    @JoinColumn(name = "STATUS")
    private Status status;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Friendship() {
    }

    public Friendship(long friendOneId, long friendTwoId, Status status) {
        this.friendOneId = friendOneId;
        this.friendTwoId = friendTwoId;
        this.status = status;
    }

    @PrePersist
    public void createdAt() {
        this.createDate = this.updateDate = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updateDate = new Date();
    }

    public long getFriendOneId() {
        return friendOneId;
    }

    public long getFriendTwoId() {
        return friendTwoId;
    }

    public Person getFriendOne() {
        return friendOne;
    }

    public Person getFriendTwo() {
        return friendTwo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
