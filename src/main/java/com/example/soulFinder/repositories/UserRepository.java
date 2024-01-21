package com.example.soulFinder.repositories;

import com.example.soulFinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByPhoneNumber(String phoneNUmber);

    List<Long> findAllById(Long postId);
}
