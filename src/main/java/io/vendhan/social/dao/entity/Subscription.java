package io.vendhan.social.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SUBSCRIPTION")
@IdClass(SubscriptionId.class)
public class Subscription implements Serializable {

    @Id
    @Column(name = "PUBLISHER")
    private long publisherId;

    @Id
    @Column(name = "SUBSCRIBER")
    private long subscriberId;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER", insertable = false, updatable = false)
    private Person publisher;

    @ManyToOne
    @JoinColumn(name = "SUBSCRIBER", insertable = false, updatable = false)
    private Person subscriber;

    @ManyToOne
    @JoinColumn(name = "STATUS")
    private Status status;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Subscription() {
    }

    public Subscription(long publisherId, long subscriberId, Status status) {
        this.publisherId = publisherId;
        this.subscriberId = subscriberId;
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

    public long getPublisherId() {
        return publisherId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public Person getPublisher() {
        return publisher;
    }

    public Person getSubscriber() {
        return subscriber;
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
