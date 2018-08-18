package io.vendhan.social.dao;

import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.repository.PersonRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class PersonDao extends
        BaseDao<PersonRepository, Person> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    @Override
    public void setJpaRepository(PersonRepository jpaRepository) {
        super.setJpaRepository(jpaRepository);
    }

    public abstract Person findByEmail(String email);

    public abstract List<Person> findByEmail(List<String> emails) throws Exception;
}
