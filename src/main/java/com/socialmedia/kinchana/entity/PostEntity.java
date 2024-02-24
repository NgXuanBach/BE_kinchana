package com.socialmedia.kinchana.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity(name = "post")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "image")
    private String image;
    @Column(name = "video")
    private String video;
    @Column(name = "date")
    private Date date;
    @Column(name = "likeQuantity")
    private int likeQuantity;
    @ManyToOne()
    @JoinColumn(name = "userId")
    UserEntity user;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "post")
    Set<CommentEntity> comments;
}
