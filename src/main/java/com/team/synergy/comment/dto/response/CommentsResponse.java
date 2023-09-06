package com.team.synergy.comment.dto.response;

import com.team.synergy.comment.Comment;
import com.team.synergy.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CommentsResponse {
    private List<Comment> comments;

    public static CommentsResponse from(List<Comment> comments) {
        return new CommentsResponse(comments);
    }
}
