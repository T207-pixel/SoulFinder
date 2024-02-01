package com.example.soulFinder.repositories;

import com.example.soulFinder.models.Post;
import com.example.soulFinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    List<Post> findAllByUser(User user);

    @Transactional
    @Modifying
    @Query("update Post p set p.isPostCheckedByAdmin = true where p.id = ?1")
    void setTrueIsPostCheckedByAdmin(Long postId);

}
