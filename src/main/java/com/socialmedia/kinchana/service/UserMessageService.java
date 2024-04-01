package com.socialmedia.kinchana.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.entity.UserMessageEntity;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.request.UpdateMessageBySendMessage;
import com.socialmedia.kinchana.payload.response.MessageResponse;
import com.socialmedia.kinchana.payload.response.UserMessageRespone;
import com.socialmedia.kinchana.payload.response.UserResponse;
import com.socialmedia.kinchana.repository.UserMessageRepository;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.UserMessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMessageService implements UserMessageServiceImp {
    @Autowired
    UserMessageRepository userMessageRepository;
    @Autowired
    UserRepository userRepository;
    Gson gson = new Gson();

    @Override
    public UserMessageRespone getUserMessageBySenderIdAndRecipientId(int senderId, int recipientId) {
        try {
            UserMessageEntity userMessageEntity = userMessageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
            if (userMessageEntity != null) {
                UserMessageRespone userMessageRespone = new UserMessageRespone();
                UserResponse sendingUser = new UserResponse();
                sendingUser.setId(userMessageEntity.getSenderId().getId());
                sendingUser.setName(userMessageEntity.getSenderId().getName());
                sendingUser.setEmail(userMessageEntity.getSenderId().getEmail());
                sendingUser.setRoleId(userMessageEntity.getSenderId().getRole().getId());
                UserResponse receivedUser = new UserResponse();
                receivedUser.setId(userMessageEntity.getRecipientId().getId());
                receivedUser.setName(userMessageEntity.getRecipientId().getName());
                receivedUser.setEmail(userMessageEntity.getRecipientId().getEmail());
                receivedUser.setRoleId(userMessageEntity.getRecipientId().getRole().getId());
                userMessageRespone.setId(userMessageEntity.getId());
                userMessageRespone.setSender(sendingUser);
                userMessageRespone.setRecipient(receivedUser);
                Type listType = new TypeToken<List<MessageResponse>>() {
                }.getType();
                List<MessageResponse> messageResponse = gson.fromJson(userMessageEntity.getContent(), listType);
                userMessageRespone.setMessageResponseList(messageResponse);
                return userMessageRespone;
            }
        } catch (Exception e) {
            throw new CustomException("Lỗi get usermessage by senderid và recipientid " + e.getMessage(), 401);
        }
        return null;
    }

    @Override
    public List<UserMessageRespone> getUserMessageBySenderId(int senderId) {
        try {
            List<UserMessageEntity> userMessageEntityList = userMessageRepository.findBySenderId(senderId);
            if (userMessageEntityList != null && !userMessageEntityList.isEmpty()) {
                List<UserMessageRespone> userMessageResponeList = new ArrayList<>();
                for (UserMessageEntity userMessageEntity : userMessageEntityList) {
                    UserMessageRespone userMessageRespone = new UserMessageRespone();
                    UserResponse sendingUser = new UserResponse();
                    sendingUser.setId(userMessageEntity.getSenderId().getId());
                    sendingUser.setName(userMessageEntity.getSenderId().getName());
                    sendingUser.setEmail(userMessageEntity.getSenderId().getEmail());
                    sendingUser.setRoleId(userMessageEntity.getSenderId().getRole().getId());
                    UserResponse receivedUser = new UserResponse();
                    receivedUser.setId(userMessageEntity.getRecipientId().getId());
                    receivedUser.setName(userMessageEntity.getRecipientId().getName());
                    receivedUser.setEmail(userMessageEntity.getRecipientId().getEmail());
                    receivedUser.setRoleId(userMessageEntity.getRecipientId().getRole().getId());
                    userMessageRespone.setId(userMessageEntity.getId());
                    userMessageRespone.setSender(sendingUser);
                    userMessageRespone.setRecipient(receivedUser);
                    Type listType = new TypeToken<List<MessageResponse>>() {
                    }.getType();
                    List<MessageResponse> messageResponse = new Gson().fromJson(userMessageEntity.getContent(), listType);
                    userMessageRespone.setMessageResponseList(messageResponse);
                    userMessageResponeList.add(userMessageRespone);
                }
                return userMessageResponeList;
            }
        } catch (Exception e) {
            throw new CustomException("Lỗi get usermessageList by senderid " + e.getMessage(), 401);
        }
        return null;
    }

    @Override
    public boolean updateMessageByConent(UpdateMessageBySendMessage updateMessageBySendMessage) {
        boolean isSuccess = false;
        try {
            UserMessageEntity userMessageEntity = userMessageRepository.findBySenderIdAndRecipientId(updateMessageBySendMessage.getSenderId(), updateMessageBySendMessage.getRecipientId());
            if (updateMessageBySendMessage.getMessageResponse() != null
                    && updateMessageBySendMessage.getMessageResponse().getContent() != null
                    && updateMessageBySendMessage.getMessageResponse().getContent() != "") {
                if (userMessageEntity == null) {
                    userMessageEntity = new UserMessageEntity();
                    List<MessageResponse> messageResponseList = new ArrayList<>();
                    messageResponseList.add(updateMessageBySendMessage.getMessageResponse());
                    userMessageEntity.setContent(gson.toJson(messageResponseList));
                    UserEntity sender = new UserEntity();
                    sender.setId(updateMessageBySendMessage.getSenderId());
                    userMessageEntity.setSenderId(sender);
                    UserEntity recipient = new UserEntity();
                    recipient.setId(updateMessageBySendMessage.getRecipientId());
                    userMessageEntity.setRecipientId(recipient);
                } else {
                    Type listType = new TypeToken<List<MessageResponse>>() {
                    }.getType();
                    List<MessageResponse> messageResponseList = new Gson().fromJson(userMessageEntity.getContent(), listType);
                    messageResponseList.add(updateMessageBySendMessage.getMessageResponse());
                    userMessageEntity.setContent(gson.toJson(messageResponseList));
                }
                userMessageRepository.save(userMessageEntity);
                isSuccess = true;
            }

        } catch (Exception e) {
            throw new CustomException("Lỗi update message user " + e.getMessage());
        }
        return isSuccess;
    }
}
