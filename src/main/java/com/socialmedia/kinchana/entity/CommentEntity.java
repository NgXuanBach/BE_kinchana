package com.socialmedia.kinchana.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "comment")

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "userid")
    UserEntity user;
    @ManyToOne()
    @JoinColumn(name = "postid")
    PostEntity post;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private Date date;
    @Column(name = "likequantity")
    private int likeQuantity;

    public CommentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikeQuantity() {
        return likeQuantity;
    }

    public void setLikeQuantity(int likeQuantity) {
        this.likeQuantity = likeQuantity;
    }
}
