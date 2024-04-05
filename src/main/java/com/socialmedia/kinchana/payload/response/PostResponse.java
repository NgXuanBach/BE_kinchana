package com.socialmedia.kinchana.payload.response;

import java.time.LocalDateTime;

public class PostResponse {
    private String content;
    private LocalDateTime date;
    private String postImage;
    private String avatar;
    private String authorName;
    private String username;
    private int likeCapacity;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikeCapacity() {
        return likeCapacity;
    }

    public void setLikeCapacity(int likeCapacity) {
        this.likeCapacity = likeCapacity;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
