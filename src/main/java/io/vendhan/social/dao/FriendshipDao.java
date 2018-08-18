package io.vendhan.social.dao;

import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.repository.FriendshipRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class FriendshipDao extends
        BaseDao<FriendshipRepository, Friendship> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    @Override
    public void setJpaRepository(FriendshipRepository jpaRepository) {
        super.setJpaRepository(jpaRepository);
    }

    public abstract Friendship getByEmails(
            String emailOne, String emailTwo) throws Exception;
}
