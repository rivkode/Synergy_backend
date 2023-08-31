package com.team.synergy.member.dto.response;

import com.team.synergy.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeInfoResponse {
    private String memberId;
    private String name;
    private String email;
    private String avatar;
    private String backgroundImage;
    private String major;
    private String temperature;
    private String bio;

    public static MeInfoResponse from(Member member) {
        return new MeInfoResponse(member.getId(), member.getName(), member.getEmail(), "", "", member.getMajor(), member.getTemperature(), member.getBio());
    }

}
