package com.socialmedia.kinchana.entity;

import com.socialmedia.kinchana.entity.ids.UserRelationshipIds;
import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "relationship")

public class RelationshipEntity {
    @EmbeddedId
    private UserRelationshipIds ids;
    @ManyToOne()
    @JoinColumn(name = "userid1", insertable = false,updatable = false)
    UserEntity user1;

    @ManyToOne()
    @JoinColumn(name = "userid2", insertable = false,updatable = false)
    UserEntity user2;
    @ManyToOne()
    @JoinColumn(name = "statusid")
    RelationShipStatusEntity relationshipStatus;
    @Column(name = "date")
    private Date date;

    public UserRelationshipIds getIds() {
        return ids;
    }

    public void setIds(UserRelationshipIds ids) {
        this.ids = ids;
    }

    public UserEntity getUser1() {
        return user1;
    }

    public void setUser1(UserEntity userId1) {
        this.user1 = userId1;
    }

    public UserEntity getUser2() {
        return user2;
    }

    public void setUser2(UserEntity userId2) {
        this.user2 = userId2;
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

    public void setDate(Date date) {
        this.date = date;
    }
}
