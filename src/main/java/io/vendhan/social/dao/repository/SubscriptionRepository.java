package io.vendhan.social.dao.repository;

import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.entity.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository
        extends JpaRepository<Subscription, SubscriptionId> {

}
