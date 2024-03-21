package com.socialmedia.kinchana.entity.ids;

import javax.persistence.*;

import java.io.Serializable;

@Embeddable
public class UserRelationshipId implements Serializable {
    @Column(name = "userid")
    private int userId;
    @Column(name = "friendid")
    private int friendId;

    public UserRelationshipId() {
    }

    public UserRelationshipId(int userId, int friendid) {
        this.userId = userId;
        this.friendId = friendid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId1) {
        this.userId = userId1;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int userId2) {
        this.friendId = userId2;
    }
}
