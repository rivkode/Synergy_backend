package com.team.synergy.follow.dto.request;

import com.team.synergy.follow.Follow;
import com.team.synergy.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateFollowRequest {
    private String followerId;
    private String followingId;

    public Follow toEntity(Member follower, Member following) {
        return new Follow(follower, following);
    }
}
