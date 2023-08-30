package com.team.synergy.projectlike.dto.response;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import com.team.synergy.projectlike.ProjectLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProjectLikeResponse {
//    private int allCount;
    private Member member;
    private Project project;

    public static CreateProjectLikeResponse from(ProjectLike projectLike) {
        return new CreateProjectLikeResponse(projectLike.getMember(), projectLike.getProject());
    }
}
