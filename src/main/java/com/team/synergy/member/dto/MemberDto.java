package com.team.synergy.member.dto;

import com.team.synergy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
                .build();
    }

}
