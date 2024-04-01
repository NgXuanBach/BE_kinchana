package com.socialmedia.kinchana.repository;

import com.socialmedia.kinchana.entity.UserMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessageEntity, Integer> {
    @Query(value = "SELECT * FROM message WHERE senderid = ?1 and  recipientid = ?2", nativeQuery = true)
    UserMessageEntity findBySenderIdAndRecipientId(int senderId, int recipientId);
    @Query(value = "SELECT * FROM message WHERE senderid = ?1", nativeQuery = true)
    List<UserMessageEntity> findBySenderId(int senderId);
}
