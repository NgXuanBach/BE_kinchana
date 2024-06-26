package com.socialmedia.kinchana.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "senderId")
    @JsonManagedReference
    private Set<UserMessageEntity> friendMessage = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
    @JsonManagedReference
    private Set<UserRelationshipXrefEntity> relationship = new HashSet<>();
    @ManyToOne()
    @JoinColumn(name = "roleid")
    RoleEntity role;
    @OneToMany(mappedBy = "user")
    Set<CommentEntity> comments;
    @OneToMany(mappedBy = "user")
    Set<PostEntity> posts;
    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthDay;

    @Column(name = "about")
    private String about;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "job")
    private String job;
    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "joined")
    private Date joined;
    @Column(name = "username")
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "coverimage")
    private String coverImage;

    @Column(name = "imagestock")
    private String imageStock;
    @Column(name = "followers")
    private Integer followersQuantity = 0;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String gmail) {
        this.email = gmail;
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

    public Set<UserRelationshipXrefEntity> getRelationship() {
        return relationship;
    }

    public void setRelationship(Set<UserRelationshipXrefEntity> friends) {
        this.relationship = friends;
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

    public Set<UserMessageEntity> getFriendMessage() {
        return friendMessage;
    }

    public void setFriendMessage(Set<UserMessageEntity> friendMessage) {
        this.friendMessage = friendMessage;
    }

    public Integer getFollowersQuantity() {
        return followersQuantity;
    }

    public void setFollowersQuantity(Integer followersQuantity) {
        this.followersQuantity = followersQuantity;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
