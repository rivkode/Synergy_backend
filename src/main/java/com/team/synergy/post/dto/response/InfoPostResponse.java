package com.team.synergy.post.dto.response;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import com.team.synergy.postlike.PostLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InfoPostResponse {
    private String title;
    private String content;
    private Member member;
    private List<PostLike> likes;

    public static InfoPostResponse from(Post post) {
        return new InfoPostResponse(post.getTitle(), post.getContent(), post.getMember(), post.getLikes());
    }
}
