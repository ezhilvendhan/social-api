package io.vendhan.social.dao;

import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.repository.SubscriptionRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class SubscriptionDao extends
        BaseDao<SubscriptionRepository, Subscription> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    @Override
    public void setJpaRepository(SubscriptionRepository jpaRepository) {
        super.setJpaRepository(jpaRepository);
    }
}
