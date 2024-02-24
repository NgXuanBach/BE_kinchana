package com.socialmedia.kinchana.entity;

import com.socialmedia.kinchana.entity.ids.UserMessageIds;
import jakarta.persistence.*;

@Entity(name = "message")

public class MessageEntity {
    @EmbeddedId
    private UserMessageIds ids;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private String timeLine;

    @ManyToOne()
    @JoinColumn(name = "userId1", insertable = false,updatable = false)
    UserEntity user1;

    @ManyToOne()
    @JoinColumn(name = "userId2", insertable = false,updatable = false)
    UserEntity user2;

}
