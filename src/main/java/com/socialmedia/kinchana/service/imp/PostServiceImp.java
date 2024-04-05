package com.socialmedia.kinchana.service.imp;

import com.socialmedia.kinchana.payload.request.PostRequest;
import com.socialmedia.kinchana.payload.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServiceImp {
    boolean addPostByToken(PostRequest postRequest);
    List<PostResponse> getAllPost();
    List<PostResponse> getPostByUsername(String username);

}
