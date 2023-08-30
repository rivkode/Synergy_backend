package com.team.synergy.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginMemberResponse {
    private String token;

    public static LoginMemberResponse from(String token) {
        return new LoginMemberResponse(token);
    }
}
