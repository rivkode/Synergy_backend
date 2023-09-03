package com.team.synergy.post.dto.request;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import lombok.Getter;

@Getter
public class CreatePostRequest {
    private String title;
    private String content;

    public Post toEntity(Member member) {
        return Post.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
