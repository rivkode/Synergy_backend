package com.team.synergy.project.dto.response;

import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProjectResponse {
    private Long projectId;

    public static CreateProjectResponse from(Project project) {
        return new CreateProjectResponse(project.getId());
    }

}
