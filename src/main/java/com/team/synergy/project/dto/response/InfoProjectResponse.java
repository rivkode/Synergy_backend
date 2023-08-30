package com.team.synergy.project.dto.response;

import com.team.synergy.apply.Apply;
import com.team.synergy.project.Project;
import com.team.synergy.projectlike.ProjectLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InfoProjectResponse {
    private String name;
    private String content;
    private String field;
    private List<ProjectLike> likes;
    private List<Apply> applyList;

    public static InfoProjectResponse from(Project project) {
        return new InfoProjectResponse(project.getName(), project.getContent(), project.getField(), project.getLikes(), project.getApplyList());
    }
}
