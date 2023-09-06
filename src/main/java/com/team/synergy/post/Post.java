package com.team.synergy.post;

import com.team.synergy.BaseTime;
import com.team.synergy.comment.Comment;
import com.team.synergy.member.Member;
import com.team.synergy.postlike.PostLike;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class Post extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    @OneToMany(
            mappedBy = "post"
    )
    private List<PostLike> likes = new ArrayList<>();

    @OneToMany(
            mappedBy = "post",
            fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();

    @Basic(fetch = FetchType.LAZY)
    @Formula("(select count(1) from post_like p where p.post_id = post_id)")
    private int postLikeCount;

    public void deletePostLike(PostLike postLike) {
        likes.removeIf(postLike1 -> postLike1.getId().equals(postLike.getId()));
    }
}
