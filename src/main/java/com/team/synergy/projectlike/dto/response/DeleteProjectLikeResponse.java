package com.team.synergy.projectlike.dto.response;

import com.team.synergy.projectlike.ProjectLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteProjectLikeResponse {
    private String memberId;

    public static DeleteProjectLikeResponse from(ProjectLike projectLike) {
        return new DeleteProjectLikeResponse(projectLike.getMember().getId());
    }

}
