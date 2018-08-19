package io.vendhan.social.dao;

import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.repository.FriendshipRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class FriendshipDao extends
        BaseDao<FriendshipRepository, Friendship> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    @Override
    public void setJpaRepository(FriendshipRepository jpaRepository) {
        super.setJpaRepository(jpaRepository);
    }

    public abstract Optional<Friendship> getByEmails(
            String emailOne, String emailTwo) throws Exception;

    public abstract List<Friendship> getFriendsByEmail(String email) throws Exception;
}
