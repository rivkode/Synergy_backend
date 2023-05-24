package com.team.synergy.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class PostDto {
    private Long id;
    private String title;
    private String content;


    public static List<PostDto> from(List<Post> allPost) {
        List<PostDto> allPostDto = new ArrayList<>();
        for(Post post : allPost) {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();
            allPostDto.add(postDto);
        }
        return allPostDto;
    }
}
