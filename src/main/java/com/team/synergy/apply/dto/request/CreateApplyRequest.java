package com.team.synergy.apply.dto.request;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.Getter;

@Getter
public class CreateApplyRequest {
    private String memberId;
    private Long projectId;

    public Apply toEntity(Member member, Project project) {
        return Apply.createApply(member, project);
    }
}
