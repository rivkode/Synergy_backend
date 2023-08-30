package com.team.synergy.project.dto.request;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProjectRequest {
    private String memberId;
    private String name;
    private String content;
    private String field;

    public Project toEntity() {
        return new Project(this.name, this.content, this.field);
    }

}
