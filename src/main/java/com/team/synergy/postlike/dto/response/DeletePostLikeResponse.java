package com.team.synergy.postlike.dto.response;

import com.team.synergy.postlike.PostLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeletePostLikeResponse {
    private String memberId;

    public static DeletePostLikeResponse from(PostLike postLike) {
        return new DeletePostLikeResponse(postLike.getMember().getId());
    }
}
