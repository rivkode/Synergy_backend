package com.team.synergy.post.dto.response;

import com.team.synergy.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePostResponse {
    private String title;
    private String content;

    public static CreatePostResponse from(Post post) {
        return new CreatePostResponse(post.getTitle(), post.getContent());
    }
}
