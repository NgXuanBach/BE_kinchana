package com.socialmedia.kinchana.repository;

import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.entity.UserRelationshipXrefEntity;
import com.socialmedia.kinchana.entity.ids.UserRelationshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationshipXrefsRepository extends JpaRepository<UserRelationshipXrefEntity, UserRelationshipId> {
    @Query(value = "SELECT * FROM user_relationship_xrefs WHERE userid = ?1", nativeQuery = true)
    List<UserRelationshipXrefEntity> findByUserId(int userId);
}
