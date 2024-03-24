package com.socialmedia.kinchana.service.imp;


import com.socialmedia.kinchana.payload.request.FindUserIdRequest;
import com.socialmedia.kinchana.payload.request.SignupRequest;
import com.socialmedia.kinchana.payload.response.LoginSigupResponse;

import java.util.List;

public interface UserServiceImp {
    void addUser(SignupRequest request);

//    boolean deleteUser(int userId);
//
//    int findUserId(FindUserIdRequest request);
//
//    boolean updateUserRole(int userId, int roleId);

//    List<UserResponse> getAllUser();
//
//    int getUserIdByToken(String token);
//    boolean changePassword(ChangePasswordRequest request);
//    UserResponse getUserByToken(String token);
//    int getRoleIdByToken(String request);
}
