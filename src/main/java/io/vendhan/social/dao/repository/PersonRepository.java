package io.vendhan.social.dao.repository;

import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long> {

}
