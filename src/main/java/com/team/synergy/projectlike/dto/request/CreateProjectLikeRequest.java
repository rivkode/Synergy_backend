package com.team.synergy.projectlike.dto.request;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import com.team.synergy.projectlike.ProjectLike;
import lombok.Getter;

@Getter
public class CreateProjectLikeRequest {
    private String memberId;

    public ProjectLike toEntity(Member member, Project project) {
        return new ProjectLike(member, project);
    }
}
