package com.socialmedia.kinchana.service.imp;

import com.socialmedia.kinchana.payload.request.UpdateMessageBySendMessage;
import com.socialmedia.kinchana.payload.response.UserMessageRespone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMessageServiceImp {
    UserMessageRespone getUserMessageBySenderIdAndRecipientId(int senderId, int recipientId);
    List<UserMessageRespone> getUserMessageBySenderId(int senderId);
    boolean updateMessageByConent(UpdateMessageBySendMessage updateMessageBySendMessage);
}
