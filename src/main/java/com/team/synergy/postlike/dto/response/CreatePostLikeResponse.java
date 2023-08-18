package com.team.synergy.postlike.dto.response;

import com.team.synergy.postlike.PostLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostLikeResponse {
    private String memberId;
    private Long postId;

    public static CreatePostLikeResponse from(PostLike postLike) {
        return new CreatePostLikeResponse(postLike.getMember().getId(), postLike.getPost().getId());
    }
}
