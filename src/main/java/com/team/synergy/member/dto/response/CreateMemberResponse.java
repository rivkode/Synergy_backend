package com.team.synergy.member.dto.response;

import com.team.synergy.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateMemberResponse {
    private String email;
    private String name;

    public static CreateMemberResponse from(Member member) {
        return new CreateMemberResponse(member.getEmail(), member.getName());
    }
}
