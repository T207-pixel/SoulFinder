package com.example.soulFinder.repositories;

import com.example.soulFinder.models.Post;
import com.example.soulFinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    List<Post> findAllByUser(User user);

}
