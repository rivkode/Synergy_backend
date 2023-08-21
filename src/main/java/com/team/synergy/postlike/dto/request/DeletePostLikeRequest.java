package com.team.synergy.postlike.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class DeletePostLikeRequest {
    private Long postId;

    @NotNull
    private String memberId;
}
