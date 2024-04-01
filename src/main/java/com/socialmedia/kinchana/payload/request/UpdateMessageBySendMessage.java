package com.socialmedia.kinchana.payload.request;

import com.socialmedia.kinchana.payload.response.MessageResponse;

public class UpdateMessageBySendMessage {
    private MessageResponse messageResponse;
    private int senderId;
    private int recipientId;

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public String toString() {
        return "UpdateMessageBySendMessage{" +
                "messageResponse=" + messageResponse +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                '}';
    }
}
