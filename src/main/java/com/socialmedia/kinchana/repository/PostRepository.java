package com.socialmedia.kinchana.repository;

import com.socialmedia.kinchana.entity.PostEntity;
import com.socialmedia.kinchana.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByUser(UserEntity user);

}
