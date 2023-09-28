package com.team.synergy.post.dto.response;

import com.team.synergy.post.Post;
import com.team.synergy.post.dto.PostGetResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListPostResponse {
    private List<InfoPostResponse> content;

    public static ListPostResponse from(List<InfoPostResponse> postGetResponses) {
        return new ListPostResponse(postGetResponses);
    }
}
