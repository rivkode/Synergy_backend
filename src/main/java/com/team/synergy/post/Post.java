package com.team.synergy.post;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private Integer likes;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    public static Post postCreate(String title, String content) {
        return Post.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();
    }
}
