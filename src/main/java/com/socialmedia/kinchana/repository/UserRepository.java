package com.socialmedia.kinchana.repository;

import com.socialmedia.kinchana.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByGmail(String email);
}
