package io.vendhan.social.dao;

import io.vendhan.social.dao.entity.Status;
import io.vendhan.social.dao.repository.StatusRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class StatusDao extends
        BaseDao<StatusRepository, Status> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    @Override
    public void setJpaRepository(StatusRepository jpaRepository) {
        super.setJpaRepository(jpaRepository);
    }
}
