package com.team.synergy.postlike;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findPostLikeByMemberAndPost(Member member, Post post);

    Optional<PostLike> findPostLikeByMemberIdAndPostId(String memberId, Long postId);

    @Query(value = "SELECT post_id FROM post_like WHERE member_id = :memberId AND post_like_status = 'POST_LIKE'", nativeQuery = true)
    List<Long> findPostIdsByMemberId(@Param("memberId") String memberId);
}
