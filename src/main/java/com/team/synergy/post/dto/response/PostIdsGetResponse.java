package com.team.synergy.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class PostIdsGetResponse {
    private List<Long> postIds;

    public static PostIdsGetResponse from(List<Long> postIds) {
        return new PostIdsGetResponse(postIds);
    }
}
