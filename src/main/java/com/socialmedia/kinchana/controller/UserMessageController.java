package com.socialmedia.kinchana.controller;

import com.google.gson.Gson;
import com.socialmedia.kinchana.payload.request.UpdateMessageBySendMessage;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import com.socialmedia.kinchana.service.imp.UserMessageServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usermessage")
@CrossOrigin("*")
public class UserMessageController {
    @Autowired
    UserMessageServiceImp userMessageServiceImp;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(UserMessageController.class);

    @GetMapping("/getbysenderandrecipient")
    public ResponseEntity<?> getBySenderAndRecipient(@RequestParam int senderId, @RequestParam int recipientId) {
        logger.info("Request :" + senderId + " and " + recipientId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userMessageServiceImp.getUserMessageBySenderIdAndRecipientId(senderId, recipientId));
        baseResponse.setMessage("Get User Message by Sender and Recipient Id");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getbysender")
    public ResponseEntity<?> getBySender(@RequestParam int senderId) {
        logger.info("Request :" + senderId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userMessageServiceImp.getUserMessageBySenderId(senderId));
        baseResponse.setMessage("Get User Message List by SenderId");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<?> sendMessage(@RequestBody UpdateMessageBySendMessage updateMessageBySendMessage) {
        logger.info("Request :" + updateMessageBySendMessage);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userMessageServiceImp.updateMessageByConent(updateMessageBySendMessage));
        baseResponse.setMessage("Update message by send message");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}

