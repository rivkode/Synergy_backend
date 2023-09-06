package com.team.synergy.member.dto.request;

import lombok.Getter;

@Getter
public class MemberSignInRequest {
    private String email;
    private String password;
}
