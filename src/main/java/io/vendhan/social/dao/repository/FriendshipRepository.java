package io.vendhan.social.dao.repository;

import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.entity.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository
        extends JpaRepository<Friendship, FriendshipId> {

}
