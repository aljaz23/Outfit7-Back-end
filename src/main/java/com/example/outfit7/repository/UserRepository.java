package com.example.outfit7.repository;

import com.example.outfit7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
