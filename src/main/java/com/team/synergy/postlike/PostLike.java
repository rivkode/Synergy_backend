package com.team.synergy.postlike;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "post_like_status")
    @Enumerated(EnumType.STRING)
    private PostLikeStatus status;

    public void setStatus(PostLikeStatus status) {
        this.status = status;
    }


    public void deletePostLike() {
        if (getStatus() == PostLikeStatus.UNLIKE) {
            throw new AppException(ErrorCode.INVALID_DATA, "이미 좋아요 취소 상태입니다");
        } else {
            this.setStatus(PostLikeStatus.UNLIKE);
        }
    }

    public PostLike(Member member, Post post) {
        this.member = member;
        this.post = post;
        this.status = PostLikeStatus.LIKE;
    }

    // 필드가 불완전한 객체 생성과 무분별한 객체 생성 체크를 하기 위해 protected 사용
    protected PostLike() {

    }
}
