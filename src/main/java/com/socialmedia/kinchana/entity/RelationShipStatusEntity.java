package com.socialmedia.kinchana.entity;

import javax.persistence.*;

import java.util.Set;

@Entity(name = "relationship_status")
public class RelationShipStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private int name;
    @OneToMany(mappedBy = "relationshipStatus")
    private Set<UserRelationshipXrefEntity> relationships;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Set<UserRelationshipXrefEntity> getRelationships() {
        return relationships;
    }

    public void setRelationships(Set<UserRelationshipXrefEntity> relationships) {
        this.relationships = relationships;
    }
}
