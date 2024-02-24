package com.socialmedia.kinchana.entity.ids;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class UserRelationshipIds implements Serializable {
    @Column(name = "userId1")
    private int user1;

    @Column(name = "userId2")
    private int user2;

    public UserRelationshipIds() {
    }

    public UserRelationshipIds(int user1, int user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int userId1) {
        this.user1 = userId1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int userId2) {
        this.user2 = userId2;
    }
}
