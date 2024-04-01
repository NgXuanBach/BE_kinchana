package com.socialmedia.kinchana.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "message")

public class UserMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderid", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity senderId;
    @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "recipientid", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity recipientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getSenderId() {
        return senderId;
    }

    public void setSenderId(UserEntity userId1) {
        this.senderId = userId1;
    }

    public UserEntity getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(UserEntity userId2) {
        this.recipientId = userId2;
    }
}
