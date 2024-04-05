package com.socialmedia.kinchana.controller;

import com.google.gson.Gson;
import com.socialmedia.kinchana.payload.request.PostRequest;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import com.socialmedia.kinchana.service.imp.PostServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {
    @Autowired
    PostServiceImp postServiceImp;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/addpost")
    public ResponseEntity<?> addPostByToken(@RequestBody PostRequest postRequest) {
        logger.info("Request :" + postRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(postServiceImp.addPostByToken(postRequest));
        baseResponse.setMessage("Add Post");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        logger.info("Request :" + "all");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(postServiceImp.getAllPost());
        baseResponse.setMessage("Get all Post");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/getbyusername")
    public ResponseEntity<?> getByUsername(@RequestParam String username) {
        logger.info("Request :" + username);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(postServiceImp.getPostByUsername(username));
        baseResponse.setMessage("Get Post By username");
        baseResponse.setStatusCode(200);
        logger.info("Response :" + gson.toJson(baseResponse));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
