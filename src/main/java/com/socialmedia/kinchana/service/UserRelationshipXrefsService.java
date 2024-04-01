package com.socialmedia.kinchana.service;

import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.entity.UserRelationshipXrefEntity;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.response.UserRelationshipXrefsResponse;
import com.socialmedia.kinchana.payload.response.UserResponse;
import com.socialmedia.kinchana.repository.UserRelationshipXrefsRepository;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.UserRelationshipXrefsServiceImp;
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
}
