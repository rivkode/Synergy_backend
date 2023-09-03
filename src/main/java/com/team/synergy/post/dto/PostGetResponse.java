package com.team.synergy.post.dto;

import com.team.synergy.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class PostGetResponse {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String authorId;
    private String authorAvatar;
    private Integer likes;

    public static Page<PostGetResponse> toResponses(Page<Post> posts) {
        Page<PostGetResponse> postGetResponses = posts.map(post -> PostGetResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getMember().getName())
                .authorId(post.getMember().getId())
                .authorAvatar("")
                .build());
        return postGetResponses;
    }
}
