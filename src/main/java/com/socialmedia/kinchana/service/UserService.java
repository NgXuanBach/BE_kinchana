package com.socialmedia.kinchana.service;

import com.socialmedia.kinchana.entity.RoleEntity;
import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.request.SignupRequest;
import com.socialmedia.kinchana.payload.response.LoginSigupResponse;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.UserServiceImp;
import com.socialmedia.kinchana.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    //    @Autowired
//    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public void addUser(SignupRequest request) {
        LoginSigupResponse response = new LoginSigupResponse();
        try {
            if (userRepository.findByEmail(request.getEmail()) != null) {
                throw new CustomException("This email have already exists.", 500);
            }
            UserEntity user = new UserEntity();
            user.setName(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setRole(new RoleEntity());
            user.getRole().setId(2);
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

//    @Override
//    public boolean deleteUser(int userId) {
//        boolean isSuccess = false;
//        try {
//            userRepository.deleteById(userId);
//            isSuccess = true;
//        } catch (Exception e) {
//            throw new CustomException("Lỗi delete user " + e.getMessage());
//        }
//        return isSuccess;
//    }
//
//    @Override
//    public int findUserId(FindUserIdRequest request) {
//        try {
//            UserEntity user = userRepository.findByEmail(request.getEmail());
//            return user.getId();
//        } catch (Exception e) {
//            throw new CustomException("Lỗi add user " + e.getMessage());
//        }
//    }
//
//    @Override
//    public boolean updateUserRole(int userId, int roleId) {
//        boolean isSuccess = false;
//        try {
//            UserEntity user = userRepository.findById(userId);
//            if (user != null && user.getRole().getId() != roleId) {
//                user.setRole(roleRepository.findById(roleId));
//                userRepository.save(user);
//                isSuccess = true;
//            }
//        } catch (Exception e) {
//            throw new CustomException("Lỗi update user role " + e.getMessage());
//        }
//        return isSuccess;
//    }
//    @Override
//    public List<UserResponse> getAllUser() {
//        try {
//            List<UserResponse> listUser = new ArrayList<>();
//            List<UserEntity> userEntityList = userRepository.findAll();
//            for (UserEntity item :
//                    userEntityList) {
//                UserResponse user = new UserResponse();
//                user.setId(item.getId());
//                user.setName(item.getUsername());
//                user.setRoleId(item.getRole().getId());
//                listUser.add(user);
//            }
//            return listUser;
//        } catch (Exception e) {
//            throw new CustomException("Lỗi add user " + e.getMessage());
//        }
//    }
//
//    @Override
//    public int getUserIdByToken(String token) {
//        try {
//            String email = jwtHelper.decodeToken(token).getSubject();
//            return userRepository.findByEmail(email).getId();
//        } catch (Exception e) {
//            throw new CustomException("Lỗi get user by token " + e.getMessage(),401);
//        }
//    }
//@Override
//    public boolean changePassword(ChangePasswordRequest request) {
//        boolean isSuccess = false;
//        try {
//            UserEntity user = userRepository.findById(request.getId());
//            System.out.println(request.getId());
//            System.out.println(passwordEncoder.encode(request.getCurrentPass()));
//            System.out.println(user.getPassword());
//            boolean passwordsMatch = BCrypt.checkpw(request.getCurrentPass(), user.getPassword());
//            System.out.println(passwordsMatch);
//
//            if(passwordsMatch){
//                user.setPassword(passwordEncoder.encode(request.getNewPass()));
//                userRepository.save(user);
//                isSuccess = true;
//            }
//            else{
//                isSuccess = false;
//            }
//
//        } catch (Exception e) {
//            throw new CustomException("Lỗi change password " + e.getMessage());
//        }
//        return isSuccess;
//    }
//    @Override
//    public UserResponse getUserByToken(String token) {
//        try {
//            UserResponse user = new UserResponse();
//            String email = jwtHelper.decodeToken(token).getSubject();
//            user.setEmail(email);
//            user.setName(userRepository.findByEmail(email).getUsername());
//            return user;
//        } catch (Exception e) {
//            throw new CustomException("Lỗi get username by token " + e.getMessage(),401);
//        }
//    }
//
//    @Override
//    public int getRoleIdByToken(String request) {
//        Claims claims = null;
//        int role = 0;
//        try {
//            claims = jwtHelper.decodeToken(request);
//            UserEntity user = userRepository.findByEmail(claims.getSubject());
//            role = user.getRole().getId();
//        }catch (Exception e){
//            throw new CustomException("Unauthorized",401);
//        }
//        return role;
//    }

}
