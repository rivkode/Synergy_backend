package com.team.synergy.follow.dto.response;

import com.team.synergy.follow.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateFollowResponse {
    private String followerId;
    private String followingId;

    public static CreateFollowResponse from(Follow follow) {
        if (follow == null) {
            return null;
        } else {
            return new CreateFollowResponse(follow.getFollower().getId(), follow.getFollowing().getId());
        }
    }
}
