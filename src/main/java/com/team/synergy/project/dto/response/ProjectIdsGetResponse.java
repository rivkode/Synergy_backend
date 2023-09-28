package com.team.synergy.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ProjectIdsGetResponse {
    private List<Long> projectIds;

    public static ProjectIdsGetResponse from(List<Long> projectIds) {
        return new ProjectIdsGetResponse(projectIds);
    }
}
