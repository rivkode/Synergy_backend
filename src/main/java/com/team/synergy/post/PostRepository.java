package com.team.synergy.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

    Page<Post> findByMemberId(Pageable pageable, String memberId);

    @Query(value = "SELECT * FROM Post WHERE post_id < :postId ORDER BY post_id DESC LIMIT 10", nativeQuery = true)
    List<Post> findAllWithId(@Param("postId") Long postId);

    @Query(value = "SELECT post_id FROM Post WHERE member_id = :memberId", nativeQuery = true)
    List<Long> findPostIdsByMemberId(@Param("memberId") String memberId);
}
