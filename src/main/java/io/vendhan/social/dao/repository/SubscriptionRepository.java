package io.vendhan.social.dao.repository;

import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.entity.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository
        extends JpaRepository<Subscription, SubscriptionId> {
    List<Subscription> findByPublisher(@Param("publisher") long publisherId);
}
