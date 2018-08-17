package io.vendhan.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDao<K extends JpaRepository, T extends Serializable> {

    public K jpaRepository;

    public K getJpaRepository() {
        return jpaRepository;
    }

    public void setJpaRepository(K jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @SuppressWarnings("unchecked")
    public void update(T entity){
        jpaRepository.saveAndFlush(entity);
    }
}
