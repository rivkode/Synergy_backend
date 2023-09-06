package com.team.synergy.apply.dto.response;

import com.team.synergy.apply.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateApplyResponse {
    private String memberId;
    private Long projectId;

    public static CreateApplyResponse from(Apply apply) {
        return new CreateApplyResponse(apply.getMember().getId(), apply.getProject().getId());
    }
}
