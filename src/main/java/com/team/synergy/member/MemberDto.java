package com.team.synergy.member;

import com.team.synergy.apply.Apply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MemberDto {
    private String name;
    private String email;
    private String password;
    private LocalDateTime createDate;
    private List<Apply> applyList = new ArrayList<>();
}
