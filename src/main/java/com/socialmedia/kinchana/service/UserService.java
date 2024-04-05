package com.socialmedia.kinchana.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socialmedia.kinchana.entity.RoleEntity;
import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.entity.UserRelationshipXrefEntity;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.request.SignupRequest;
import com.socialmedia.kinchana.payload.response.ImageStockResponse;
import com.socialmedia.kinchana.payload.response.LoginSigupResponse;
import com.socialmedia.kinchana.payload.response.UserResponse;
import com.socialmedia.kinchana.repository.UserRelationshipXrefsRepository;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.UserServiceImp;
import com.socialmedia.kinchana.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserRelationshipXrefsRepository userRelationshipXrefsRepository;
    Gson gson = new Gson();

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
            user.setUsername(request.getUsername());
            user.setAvatar("noprofile.jpeg");
            user.setCoverImage("nocover.jpg");
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
    @Override
    public List<UserResponse> getAllUser() {
        try {
            List<UserResponse> listUser = new ArrayList<>();
            List<UserEntity> userEntityList = userRepository.findAll();
            for (UserEntity item :
                    userEntityList) {
                UserResponse user = new UserResponse();
                user.setId(item.getId());
                user.setName(item.getName());
                user.setRoleId(item.getRole().getId());
                user.setEmail(item.getEmail());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi add user " + e.getMessage());
        }
    }

    @Override
    public UserResponse getUserByName(String userName) {
        try {
            UserEntity userEntity = userRepository.findByName(userName);
            if (userEntity != null) {
                UserResponse user = new UserResponse();
                user.setId(userEntity.getId());
                user.setEmail(userEntity.getEmail());
                user.setName(userEntity.getName());
                user.setUsername(userEntity.getUsername());
                user.setRoleId(userEntity.getRole().getId());
                return user;
            }
        } catch (Exception e) {
            throw new CustomException("Lỗi get user by name " + e.getMessage(), 401);
        }
        return null;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        try {
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity != null) {
                UserResponse user = new UserResponse();
                user.setId(userEntity.getId());
                user.setEmail(userEntity.getEmail());
                user.setName(userEntity.getName());
                user.setUsername(userEntity.getUsername());
                user.setAbout(userEntity.getAbout());
                user.setHobbies(userEntity.getHobbies());
                user.setJob(userEntity.getJob());
                user.setAddress(userEntity.getAddress());
                user.setBirthDay(userEntity.getBirthDay());
                user.setJoined(userEntity.getJoined());
                user.setGender(userEntity.getGender());
                user.setImageStock(userEntity.getImageStock());
                user.setPhoneNumber(userEntity.getPhoneNumber());
                if (userEntity.getFollowersQuantity() != null)
                    user.setFollowersNumber(userEntity.getFollowersQuantity());
                if (userEntity.getPosts() != null)
                    user.setPostsNumber(userEntity.getPosts().size());
                if (userEntity.getRelationship() != null) {
                    int followingNumber = 0;
                    int friendNumber = 0;
                    for (UserRelationshipXrefEntity userRelationshipXrefEntity : userEntity.getRelationship()) {
                        if (userRelationshipXrefEntity.getRelationshipStatus().getName().equalsIgnoreCase("follow"))
                            followingNumber++;
                        if (userRelationshipXrefEntity.getRelationshipStatus().getName().equalsIgnoreCase("friend"))
                            friendNumber++;
                    }
                    user.setFriendsCapacity(friendNumber);
                    user.setAvatar(userEntity.getAvatar());
                    user.setCoverImage(userEntity.getCoverImage());
                    user.setFollowingNumber(followingNumber);
                }
                user.setRoleId(userEntity.getRole().getId());
                return user;
            }
        } catch (Exception e) {
            throw new CustomException("Lỗi get user by name " + e.getMessage(), 401);
        }
        return null;
    }

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
    @Override
    public UserResponse getUserByToken(String token) {
        try {
            UserResponse user = new UserResponse();
            String email = jwtHelper.decodeToken(token).getSubject();
            UserEntity userEntity = userRepository.findByEmail(email);
            user.setEmail(userEntity.getEmail());
            user.setGender(userEntity.getGender());
            user.setAddress(userEntity.getAddress());
            user.setHobbies(userEntity.getHobbies());
            user.setAbout(userEntity.getAbout());
            user.setPassword(userEntity.getPassword());
            user.setJob(userEntity.getJob());
            user.setId(userEntity.getId());
            user.setPhoneNumber(userEntity.getPhoneNumber());
            user.setBirthDay(userEntity.getBirthDay());
            user.setUsername(userEntity.getUsername());
            user.setJoined(userEntity.getJoined());
            user.setRoleId(userEntity.getRole().getId());
            user.setAvatar(userEntity.getAvatar());
            user.setName(userRepository.findByEmail(email).getName());
            return user;
        } catch (Exception e) {
            throw new CustomException("Lỗi get username by token " + e.getMessage(), 401);
        }
    }

    @Override
    public UserResponse getUserById(int id) {
        try {
            UserResponse user = new UserResponse();
            UserEntity userEntity = userRepository.findById(id);
            user.setEmail(userEntity.getEmail());
            user.setId(userEntity.getId());
            user.setRoleId(userEntity.getRole().getId());
            user.setName(userEntity.getName());
            return user;
        } catch (Exception e) {
            throw new CustomException("Lỗi get username by id " + e.getMessage(), 401);
        }
    }

    @Override
    public boolean updateAvatarByUsername(String fileName, String username) {
        boolean isSuccess = false;
        try {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity.getImageStock() == null) {
                List<ImageStockResponse> imageStockResponseList = new ArrayList<>();
                imageStockResponseList.add(new ImageStockResponse(fileName, now.toString()));
                userEntity.setImageStock(gson.toJson(imageStockResponseList));
            } else {
                Type listType = new TypeToken<List<ImageStockResponse>>() {
                }.getType();
                List<ImageStockResponse> imageStockResponseList = new Gson().fromJson(userEntity.getImageStock(), listType);
                imageStockResponseList.add(new ImageStockResponse(fileName, now.toString()));
                userEntity.setImageStock(gson.toJson(imageStockResponseList));
            }
            if (userEntity.getAvatar().equalsIgnoreCase(fileName))
                return isSuccess;
            userEntity.setAvatar(fileName);
            userRepository.save(userEntity);
            isSuccess = true;
        } catch (
                Exception e) {
            throw new CustomException("Error update avatar: " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean updateCoverImageByUsername(String fileName, String username) {
        boolean isSuccess = false;
        try {
            UserEntity user = userRepository.findByUsername(username);
            if (user.getCoverImage().equalsIgnoreCase(fileName))
                return isSuccess;
            user.setCoverImage(fileName);
            userRepository.save(user);
            isSuccess = true;
        } catch (
                Exception e) {
            throw new CustomException("Error update cover image: " + e.getMessage());
        }
        return isSuccess;
    }
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
