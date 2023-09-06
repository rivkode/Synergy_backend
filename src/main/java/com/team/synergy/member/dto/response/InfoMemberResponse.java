package com.team.synergy.member.dto.response;

import com.team.synergy.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InfoMemberResponse {
    private String memberId;
    private String name;
    private String email;
    private String avatar;
    private String backgroundImage;
    private String major;
    private String temperature;
    private String bio;

    public static InfoMemberResponse from(Member member) {
        return new InfoMemberResponse(
                member.getId(), member.getName(), member.getEmail(), "", "", member.getMajor(), member.getTemperature(), member.getBio()
        );
    }
}
