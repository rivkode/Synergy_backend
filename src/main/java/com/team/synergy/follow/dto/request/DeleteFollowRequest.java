package com.team.synergy.follow.dto.request;

import lombok.Getter;

@Getter
public class DeleteFollowRequest {
    private String followingId;
    private String followerId;
}
