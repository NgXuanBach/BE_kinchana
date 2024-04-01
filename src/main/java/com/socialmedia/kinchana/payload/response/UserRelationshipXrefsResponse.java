package com.socialmedia.kinchana.payload.response;

import java.sql.Date;

public class UserRelationshipXrefsResponse {
    private int userId;
    private UserResponse friend;
    private String statusName;
    private Date date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserResponse getFriend() {
        return friend;
    }

    public void setFriend(UserResponse friend) {
        this.friend = friend;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
