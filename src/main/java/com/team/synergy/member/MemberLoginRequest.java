package com.team.synergy.member;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    private String name;
    private String email;
    private String password;
}
