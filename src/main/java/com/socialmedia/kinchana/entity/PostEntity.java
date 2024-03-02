package com.socialmedia.kinchana.entity;

import javax.persistence.*;

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
    @Column(name = "likequantity")
    private int likeQuantity;
    @ManyToOne()
    @JoinColumn(name = "userid")
    UserEntity user;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "post")
    Set<CommentEntity> comments;
}
