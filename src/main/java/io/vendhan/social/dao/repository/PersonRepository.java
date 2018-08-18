package io.vendhan.social.dao.repository;

import io.vendhan.social.dao.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long> {

    List<Person> findByEmail(@Param("email") String email);

}