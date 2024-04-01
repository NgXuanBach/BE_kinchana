package com.socialmedia.kinchana.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialmedia.kinchana.entity.ids.UserRelationshipId;

import javax.persistence.*;

import java.sql.Date;

@Entity(name = "user_relationship_xrefs")
public class UserRelationshipXrefEntity {
    @EmbeddedId
    private UserRelationshipId id;
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @JsonBackReference
        private UserEntity userId;
    @MapsId("friendId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendid", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity friendId;
    @ManyToOne()
    @JoinColumn(name = "statusid")
    RelationShipStatusEntity relationshipStatus;
    @Column(name = "date")
    private Date date;


    public UserRelationshipId getId() {
        return id;
    }

    public void setId(UserRelationshipId id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public UserEntity getFriendId() {
        return friendId;
    }

    public void setFriendId(UserEntity friendId) {
        this.friendId = friendId;
    }

    public RelationShipStatusEntity getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationShipStatusEntity relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date dateTime) {
        this.date = dateTime;
    }
}
