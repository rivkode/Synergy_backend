package com.team.synergy.apply.dto.request;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import lombok.Getter;

@Getter
public class DeleteApplyRequest {
    private String memberId;
    private Long projectId;
}
