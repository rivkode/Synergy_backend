package com.team.synergy.post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;


}
