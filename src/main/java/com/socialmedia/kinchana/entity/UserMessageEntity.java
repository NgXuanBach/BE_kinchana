package com.socialmedia.kinchana.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "message")

public class UserMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private String timeLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid1", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity userId1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid2", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity userId2;

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

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }

    public UserEntity getUserId1() {
        return userId1;
    }

    public void setUserId1(UserEntity userId1) {
        this.userId1 = userId1;
    }

    public UserEntity getUserId2() {
        return userId2;
    }

    public void setUserId2(UserEntity userId2) {
        this.userId2 = userId2;
    }
}
