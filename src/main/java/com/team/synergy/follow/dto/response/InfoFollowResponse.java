package com.team.synergy.follow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InfoFollowResponse {
    private List<String> followers;
    private List<String> followings;

    public static InfoFollowResponse from(List<String> followers, List<String> followings) {
        return new InfoFollowResponse(followers, followings);
    }
}
