package com.team.synergy.member.dto.response;

import com.team.synergy.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
@Builder
public class MemberGetResponse {
    private String id;
    private String name;
    private String email;

    public static Page<MemberGetResponse> toResponse(Page<Member> members) {
        Page<MemberGetResponse> memberGetResponses = members.map(m -> MemberGetResponse.builder()
                .id(m.getId())
                .name(m.getName())
                .email(m.getEmail())
                .build());
        return memberGetResponses;
    }
}
