package com.team.synergy.comment;

import com.team.synergy.BaseTime;
import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void addMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }

    public void deletePost() {
        this.post.getComments().removeIf(comment -> comment.getId().equals(this.id));
    }

    public void deleteMember() {
        this.member.getComments().removeIf(comment -> comment.getId().equals(this.id));
    }

    public Comment(String content, Member member, Post post) {
        this.content = content;
        this.member = member;
        this.post = post;
    }

    protected Comment() {

    }
}
