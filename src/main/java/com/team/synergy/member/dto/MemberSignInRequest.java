package com.team.synergy.member.dto;

import lombok.Getter;

@Getter
public class MemberSignInRequest {
    private String email;
    private String password;
}
