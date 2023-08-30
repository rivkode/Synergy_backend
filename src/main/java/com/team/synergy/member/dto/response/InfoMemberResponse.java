package com.team.synergy.member.dto.response;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InfoMemberResponse {
    private String name;
    private String email;
    private List<Post> posts;
    private List<Apply> applyList;

    public static InfoMemberResponse from(Member member) {
        return new InfoMemberResponse(member.getName(), member.getEmail(), member.getPosts(), member.getApplyList());
    }

}
