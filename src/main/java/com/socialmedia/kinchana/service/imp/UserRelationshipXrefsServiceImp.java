package com.socialmedia.kinchana.service.imp;

import com.socialmedia.kinchana.payload.response.Acquaintance;
import com.socialmedia.kinchana.payload.response.UserRelationshipXrefsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface UserRelationshipXrefsServiceImp {
    List<UserRelationshipXrefsResponse> getUserRelationshipByUserId(int id);
    List<Acquaintance> getFriendAndFollowRelationshipByUsername(String username);
}
