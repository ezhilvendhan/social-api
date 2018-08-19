package io.vendhan.social.dao.repository;

import io.vendhan.social.dao.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository
        extends JpaRepository<Status, Long> {

    List<Status> findByLabel(@Param("label") String label);
}
