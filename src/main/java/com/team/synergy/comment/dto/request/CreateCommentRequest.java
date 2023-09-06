package com.team.synergy.comment.dto.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String comment;
    private Long postId;
}
