package com.team.synergy.apply.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProjectIdsResponse {
    private int count;
    private List<String> memberIds;

    public static ProjectIdsResponse createWithMemberIds(List<String> memberIds) {
        return new ProjectIdsResponse(memberIds.size(), memberIds);
    }
}
