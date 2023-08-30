package com.team.synergy.post.dto;

import com.team.synergy.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class PostGetResponse {
    private Long id;

    private String title;

    private String content;

    private Integer likes;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    public static Page<PostGetResponse> toResponses(Page<Post> posts) {
        Page<PostGetResponse> postGetResponses = posts.map(m -> PostGetResponse.builder()
                .id(m.getId())
                .title(m.getTitle())
                .content(m.getContent())
                .createDate(m.getCreatedDate())
                .build());
        return postGetResponses;
    }
}
