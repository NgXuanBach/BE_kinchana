package com.socialmedia.kinchana.controller;

import com.google.gson.Gson;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUser() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getAllUser());
        baseResponse.setMessage("Get All User");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getuserbyname")
    public ResponseEntity<?> getUserByName(@RequestParam String name) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUserByName(name));
        baseResponse.setMessage("Get User By Name");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getuserbytoken")
    public ResponseEntity<?> getUserByToken(@RequestParam String token) {
        logger.info("Request :" + token);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUserByToken(token));
        baseResponse.setMessage("Get user by token");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getuserbyid")
    public ResponseEntity<?> getUserByName(@RequestParam int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUserById(id));
        baseResponse.setMessage("Get User By Id");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getuserbyusername")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        logger.info("Request :" + username);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUserByUsername(username));
        baseResponse.setMessage("Get user by username");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/updateavatar")
    public ResponseEntity<?> updateAvatar(@RequestParam String fileName, @RequestParam String username) {
        logger.info("Request :" + fileName);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.updateAvatarByUsername(fileName, username));
        baseResponse.setMessage("Update avatar user");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @PostMapping("/updatecoverimage")
    public ResponseEntity<?> updateCoverImage(@RequestParam String fileName, @RequestParam String username) {
        logger.info("Request :" + fileName);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.updateCoverImageByUsername(fileName, username));
        baseResponse.setMessage("Update cover image user");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
