package com.team.synergy.postlike;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findPostLikeByMemberAndPost(Member member, Post post);

    Optional<PostLike> findPostLikeByMemberIdAndPostId(String memberId, Long postId);
}
