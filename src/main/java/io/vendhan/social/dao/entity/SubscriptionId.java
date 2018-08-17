package io.vendhan.social.dao.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SubscriptionId implements Serializable {
    private long publisherId;

    private long subscriberId;

    public SubscriptionId() {}

    public SubscriptionId(long publisherId, long subscriberId) {
        this.publisherId = publisherId;
        this.subscriberId = subscriberId;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }
}
