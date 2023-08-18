package com.team.synergy.postlike.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeletePostLikeResponse {

    public static DeletePostLikeResponse from() {
        return new DeletePostLikeResponse();
    }
}
