package com.team.synergy.apply.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MemberIdsResponse {
    private int count;
    private List<Long> projectIds;

    public static MemberIdsResponse createWithProjectIds(List<Long> projectIds) {
        return new MemberIdsResponse(projectIds.size(), projectIds);
    }
}
