package com.socialmedia.kinchana.service;

import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.entity.UserRelationshipXrefEntity;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.response.Acquaintance;
import com.socialmedia.kinchana.payload.response.UserRelationshipXrefsResponse;
import com.socialmedia.kinchana.payload.response.UserResponse;
import com.socialmedia.kinchana.repository.UserRelationshipXrefsRepository;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.UserRelationshipXrefsServiceImp;
import com.socialmedia.kinchana.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRelationshipXrefsService implements UserRelationshipXrefsServiceImp {
    @Autowired
    UserRelationshipXrefsRepository userRelationshipXrefsRepositoryRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtHelper jwtHelper;
    @Override
    public List<UserRelationshipXrefsResponse> getUserRelationshipByUserId(int id) {
        try {
            List<UserRelationshipXrefsResponse> UserRelationShipXrefsList = new ArrayList<>();
            List<UserRelationshipXrefEntity> userRelationshipXrefEntities = userRelationshipXrefsRepositoryRepo.findByUserId(id);
            for (UserRelationshipXrefEntity items :
                    userRelationshipXrefEntities) {
                UserRelationshipXrefsResponse relationship = new UserRelationshipXrefsResponse();
                relationship.setUserId(items.getUserId().getId());
                UserResponse friendUserResponse = new UserResponse();
                UserEntity userEntity = userRepository.findById(items.getFriendId().getId());
                friendUserResponse.setId(userEntity.getId());
                friendUserResponse.setName(userEntity.getName());
                friendUserResponse.setRoleId(userEntity.getRole().getId());
                friendUserResponse.setEmail(userEntity.getEmail());
                relationship.setFriend(friendUserResponse);
                relationship.setStatusName(items.getRelationshipStatus().getName());
                relationship.setDate(items.getDate());
                UserRelationShipXrefsList.add(relationship);
            }
            return UserRelationShipXrefsList;
        } catch (Exception e) {
            throw new CustomException("Lỗi getUserRelationshipByUserId  " + e.getMessage());
        }
    }

    @Override
    public List<Acquaintance> getFriendAndFollowRelationshipByUsername(String username) {
        try {
            List<Acquaintance> userResponseList = new ArrayList<>();
            List<UserRelationshipXrefEntity> userRelationshipXrefEntities = userRelationshipXrefsRepositoryRepo.findByFriendId(userRepository.findByUsername(username).getId());
            for (UserRelationshipXrefEntity items :
                    userRelationshipXrefEntities) {
                if(items.getRelationshipStatus().getName().equalsIgnoreCase("friend")
                        || items.getRelationshipStatus().getName().equalsIgnoreCase("follow") ){
                    Acquaintance acquaintance = new Acquaintance();
                    acquaintance.setId(items.getUserId().getId());
                    acquaintance.setName(items.getUserId().getName());
                    if(items.getUserId().getPosts() != null) {
                        acquaintance.setPostsNumber(items.getUserId().getPosts().size());
                    }
                    if(items.getUserId().getRelationship() != null){
                        int followingNumber = 0;
                        int friendNumber = 0;
                        for (UserRelationshipXrefEntity userRelationshipXrefEntity : items.getUserId().getRelationship()) {
                            if (userRelationshipXrefEntity.getRelationshipStatus().getName().equalsIgnoreCase("follow"))
                                followingNumber++;
                            if (userRelationshipXrefEntity.getRelationshipStatus().getName().equalsIgnoreCase("friend"))
                                friendNumber++;
                        }
                        acquaintance.setFriendsCapacity(friendNumber);
                    }
                    acquaintance.setRelationshipStatus(items.getRelationshipStatus().getName());
                    acquaintance.setFollowersNumber(items.getUserId().getFollowersQuantity());
                    acquaintance.setUsername(items.getUserId().getUsername());
                    acquaintance.setJoined(items.getUserId().getJoined());
                    acquaintance.setAddress(items.getUserId().getAddress());
                    acquaintance.setAvatar(items.getUserId().getAvatar());
                    userResponseList.add(acquaintance);
                }
            }
            return userResponseList;
        } catch (Exception e) {
            throw new CustomException("Lỗi getUserRelationshipByFriendId  " + e.getMessage());
        }
    }
}
