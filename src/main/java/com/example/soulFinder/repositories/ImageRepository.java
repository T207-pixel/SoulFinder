package com.example.soulFinder.repositories;

import com.example.soulFinder.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
