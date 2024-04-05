package com.socialmedia.kinchana.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socialmedia.kinchana.entity.PostEntity;
import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.payload.request.PostRequest;
import com.socialmedia.kinchana.payload.response.ImageStockResponse;
import com.socialmedia.kinchana.payload.response.MessageResponse;
import com.socialmedia.kinchana.payload.response.PostResponse;
import com.socialmedia.kinchana.repository.PostRepository;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.service.imp.PostServiceImp;
import com.socialmedia.kinchana.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements PostServiceImp {
    @Autowired
    PostRepository postRepository;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserRepository userRepository;
    Gson gson = new Gson();
    @Override
    public boolean addPostByToken(PostRequest postRequest) {
        boolean isSuccess = false;
        try {
            UserEntity userEntity = userRepository.findByEmail(jwtHelper.decodeToken(postRequest.getUserToken()).getSubject());
            if(userEntity.getImageStock() == null ){
                List<ImageStockResponse> imageStockResponseList = new ArrayList<>();
                imageStockResponseList.add(new ImageStockResponse(postRequest.getImage(),postRequest.getDate().toString()));
                userEntity.setImageStock(gson.toJson(imageStockResponseList));
            }else {
                Type listType = new TypeToken<List<ImageStockResponse>>() {
                }.getType();
                List<ImageStockResponse> imageStockResponseList = new Gson().fromJson(userEntity.getImageStock(), listType);
                imageStockResponseList.add(new ImageStockResponse(postRequest.getImage(),postRequest.getDate().toString()));
                userEntity.setImageStock(gson.toJson(imageStockResponseList));
            }
            PostEntity postEntity = new PostEntity();
            postEntity.setContent(postRequest.getContent());
            postEntity.setLikeQuantity(0);
            postEntity.setUser(userEntity);
            postEntity.setImage(postRequest.getImage());
            postEntity.setDate(postRequest.getDate());
            postRepository.save(postEntity);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public List<PostResponse> getAllPost() {
        try {
            if (postRepository.findAll() != null) {
                List<PostResponse> postResponseList = new ArrayList<>();
                for (PostEntity postEntity :
                        postRepository.findAll()) {
                    PostResponse postResponse = new PostResponse();
                    postResponse.setContent(postEntity.getContent());
                    postResponse.setLikeCapacity(postEntity.getLikeQuantity());
                    postResponse.setAuthorName(postEntity.getUser().getName());
                    postResponse.setPostImage(postEntity.getImage());
                    postResponse.setDate(postEntity.getDate());
                    postResponse.setUsername(postEntity.getUser().getUsername());
                    postResponse.setAvatar(postEntity.getUser().getAvatar());
                    postResponseList.add(postResponse);
                }
                return postResponseList;
            }
            return null;
        } catch (Exception e) {
            throw new CustomException("Lỗi getALlPost" + e.getMessage());
        }
    }

    @Override
    public List<PostResponse> getPostByUsername(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username);
            if (postRepository.findByUser(user) != null) {
                List<PostResponse> postResponseList = new ArrayList<>();
                for (PostEntity postEntity :
                        postRepository.findAll()) {
                    PostResponse postResponse = new PostResponse();
                    postResponse.setContent(postEntity.getContent());
                    postResponse.setLikeCapacity(postEntity.getLikeQuantity());
                    postResponse.setAuthorName(postEntity.getUser().getName());
                    postResponse.setPostImage(postEntity.getImage());
                    postResponse.setDate(postEntity.getDate());
                    postResponse.setUsername(postEntity.getUser().getUsername());
                    postResponse.setAvatar(postEntity.getUser().getAvatar());
                    postResponseList.add(postResponse);
                }
                return postResponseList;
            }
            return null;
        } catch (Exception e) {
            throw new CustomException("Lỗi getPostByUsername" + e.getMessage());
        }
    }
}
