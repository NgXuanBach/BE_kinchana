package com.socialmedia.kinchana.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity(name = "user")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "password")
    private String password;

    @ManyToOne()
    @JoinColumn(name = "roleId")
    RoleEntity role;

    @OneToMany(mappedBy = "user1")
    Set<MessageEntity> message;
    @OneToMany(mappedBy = "user2")
    Set<MessageEntity> message2;
    @OneToMany(mappedBy = "user1")
    Set<RelationshipEntity> relationship1;
    @OneToMany(mappedBy = "user2")
    Set<RelationshipEntity> relationship2;
    @OneToMany(mappedBy = "user")
    Set<CommentEntity> comments;
    @OneToMany(mappedBy = "user")
    Set<PostEntity> posts;
    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date birthDay;

    @Column(name = "about")
    private String about;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "job")
    private String job;

    @Column(name = "joined")
    private Date joined;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "coverImage")
    private String coverImage;

    @Column(name = "imageStock")
    private String imageStock;

    @Column(name = "followers")
    private int followersQuantity;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getImageStock() {
        return imageStock;
    }

    public void setImageStock(String imageStock) {
        this.imageStock = imageStock;
    }

    public int getFollowersQuantity() {
        return followersQuantity;
    }

    public void setFollowersQuantity(int followersQuantity) {
        this.followersQuantity = followersQuantity;
    }

    public Set<MessageEntity> getMessage() {
        return message;
    }

    public void setMessage(Set<MessageEntity> message) {
        this.message = message;
    }

    public Set<MessageEntity> getMessage2() {
        return message2;
    }

    public void setMessage2(Set<MessageEntity> message2) {
        this.message2 = message2;
    }

    public Set<RelationshipEntity> getRelationship1() {
        return relationship1;
    }

    public void setRelationship1(Set<RelationshipEntity> relationship1) {
        this.relationship1 = relationship1;
    }

    public Set<RelationshipEntity> getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(Set<RelationshipEntity> relationship2) {
        this.relationship2 = relationship2;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }
}
