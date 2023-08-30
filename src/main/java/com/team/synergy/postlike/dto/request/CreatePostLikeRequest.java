package com.team.synergy.postlike.dto.request;

import com.sun.istack.NotNull;
import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import com.team.synergy.postlike.PostLike;
import lombok.Getter;

@Getter
public class CreatePostLikeRequest {

    @NotNull
    private String memberId;

    public PostLike toEntity(Member member, Post post) {
        return new PostLike(member, post);
    }
}
