package com.team.synergy.post.dto.response;

import com.team.synergy.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class InfoPostResponse {
    private Long postId;
    private String title;
    private String content;
    private String authorName;
    private String authorId;
    private String authorAvatar;
    private int likes;

    public static InfoPostResponse from(Post post) {
        return new InfoPostResponse(post.getId() ,post.getTitle(), post.getContent(), post.getMember().getName(), post.getMember().getId(), "", post.getLikes().size());
    }
}
