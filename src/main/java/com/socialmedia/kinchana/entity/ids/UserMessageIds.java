package com.socialmedia.kinchana.entity.ids;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class UserMessageIds implements Serializable {
    @Column(name = "userid1")
    private int user1;

    @Column(name = "userid2")
    private int user2;

    public UserMessageIds(int user1, int user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public UserMessageIds() {
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }
}
