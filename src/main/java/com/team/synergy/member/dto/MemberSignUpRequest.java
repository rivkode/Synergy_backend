package com.team.synergy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberSignUpRequest {
    private String name;
    private String password;
    private String email;
}
