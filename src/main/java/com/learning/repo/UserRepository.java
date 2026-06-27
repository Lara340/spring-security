package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndIsActive(String username, boolean isActive);
}
