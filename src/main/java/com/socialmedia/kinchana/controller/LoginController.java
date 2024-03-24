package com.socialmedia.kinchana.controller;

import com.google.gson.Gson;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.request.SigninRequest;
import com.socialmedia.kinchana.payload.request.SignupRequest;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import com.socialmedia.kinchana.provider.CustomAuthenticateProvider;
import com.socialmedia.kinchana.service.imp.UserServiceImp;
import com.socialmedia.kinchana.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    UserServiceImp userServiceImp;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/signin",method= RequestMethod.POST)
    public ResponseEntity<?> sigin(@RequestBody SigninRequest request){
        logger.info("Request:"+gson.toJson(request));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        authenticationManager.authenticate(token);
        BaseResponse response = new BaseResponse();
        response.setData(jwtHelper.generateToken(request.getEmail()));
        response.setMessage("Authenticated");
        response.setStatusCode(200);
        logger.info("Response:"+gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request, BindingResult result) {
        logger.info("Request :" + gson.toJson(request));
        List<FieldError> list = result.getFieldErrors();
        for (FieldError data :
                list) {
            throw new CustomException(data.getDefaultMessage());
        }
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> dataList = new HashMap<>();
        userServiceImp.addUser(request);
        dataList.put("token", jwtHelper.generateToken(request.getEmail()));
        response.setStatusCode(200);
        response.setMessage("Saved user");
        response.setData(dataList);
        logger.info("Response :" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
