package com.socialmedia.kinchana.payload.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserMessageRespone {
    private int id;
    private UserResponse sender;
    private UserResponse recipient;
    private List<MessageResponse> messageResponseList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserResponse getSender() {
        return sender;
    }

    public void setSender(UserResponse sender) {
        this.sender = sender;
    }

    public UserResponse getRecipient() {
        return recipient;
    }

    public void setRecipient(UserResponse recipient) {
        this.recipient = recipient;
    }

    public void setMessageResponseList(List<MessageResponse> messageResponseList) {
        this.messageResponseList = messageResponseList;
    }

    public List<MessageResponse> getMessageResponseList() {
        return messageResponseList;
    }
}
