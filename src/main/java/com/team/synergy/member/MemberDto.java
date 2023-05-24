package com.team.synergy.member;

import com.team.synergy.apply.Apply;
import lombok.Builder;
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

    @Builder
    public MemberDto(String name, String email, String password, LocalDateTime createDate){
        this.name = name;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
    }
    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .createDate(member.getCreateDate())
                .build();
    }

}
