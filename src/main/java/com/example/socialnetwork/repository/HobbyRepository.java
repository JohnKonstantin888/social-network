package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HobbyRepository extends JpaRepository<Hobby, UUID> {
}