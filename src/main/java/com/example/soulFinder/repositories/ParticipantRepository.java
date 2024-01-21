package com.example.soulFinder.repositories;

import com.example.soulFinder.models.Participant;
import com.example.soulFinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query(value = "SELECT u.* FROM users u JOIN participants pp ON u.id = pp.user_id WHERE pp.post_id = :postId", nativeQuery = true)
    List<Long> findUsersByPostId(@Param("postId") Long postId);

}
