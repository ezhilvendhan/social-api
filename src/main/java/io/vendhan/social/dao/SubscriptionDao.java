package io.vendhan.social.dao;

import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.repository.SubscriptionRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class SubscriptionDao extends
        BaseDao<SubscriptionRepository, Subscription> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    @Override
    public void setJpaRepository(SubscriptionRepository jpaRepository) {
        super.setJpaRepository(jpaRepository);
    }

    public abstract List<Subscription> getSubscriptions(String publisherEmail) throws Exception;

    public abstract Optional<Subscription> getSubscription(
            String subscriberEmail, String publisherEmail) throws Exception;

    public abstract boolean isBlocked(
            String subscriberEmail, String publisherEmail) throws Exception;

    public abstract List<String> getSubscribers(
            String publisherEmail) throws Exception;
}
