package com.socialmedia.kinchana.repository;

import com.socialmedia.kinchana.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    UserEntity findByName(String name);
    UserEntity findById(int id);
}
