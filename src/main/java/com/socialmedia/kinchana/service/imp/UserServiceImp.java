package com.socialmedia.kinchana.service.imp;


import com.socialmedia.kinchana.payload.request.FindUserIdRequest;
import com.socialmedia.kinchana.payload.request.SignupRequest;
import com.socialmedia.kinchana.payload.response.LoginSigupResponse;
import com.socialmedia.kinchana.payload.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServiceImp {
    void addUser(SignupRequest request);

//    boolean deleteUser(int userId);
//
//    int findUserId(FindUserIdRequest request);
//
//    boolean updateUserRole(int userId, int roleId);

    List<UserResponse> getAllUser();
    UserResponse getUserByName(String userName);
//
//    int getUserIdByToken(String token);
//    boolean changePassword(ChangePasswordRequest request);
    UserResponse getUserByToken(String token);
    UserResponse getUserById(int id);
//    int getRoleIdByToken(String request);

}
