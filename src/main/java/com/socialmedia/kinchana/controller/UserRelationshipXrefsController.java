package com.socialmedia.kinchana.controller;

import com.google.gson.Gson;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import com.socialmedia.kinchana.service.imp.UserRelationshipXrefsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userrelationship")
@CrossOrigin("*")
public class UserRelationshipXrefsController {
    @Autowired
    UserRelationshipXrefsServiceImp userRelationshipXrefsServiceImp;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(UserRelationshipXrefsController.class);

    @GetMapping("/getbyuserid")
    public ResponseEntity<?> getUserByUserId(@RequestParam int userId){
        logger.info("Request :" + userId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userRelationshipXrefsServiceImp.getUserRelationshipByUserId(userId));
        baseResponse.setMessage("Get User Relationship By UserId");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/getbyusername")
    public ResponseEntity<?> getUserFriendId(@RequestParam String username){
        logger.info("Request :" + username);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userRelationshipXrefsServiceImp.getFriendAndFollowRelationshipByUsername(username));
        baseResponse.setMessage("Get User Relationship By Username");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
