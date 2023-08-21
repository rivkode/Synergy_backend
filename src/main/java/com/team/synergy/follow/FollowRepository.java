package com.team.synergy.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query(value = "SELECT following_id FROM follow WHERE follower_id = :id", nativeQuery = true)
    List<String> findFollowingIdsByFollowerId(@Param("id") String id);

    @Query(value = "SELECT * FROM follow WHERE follower_id = :followerId AND following_id = :followingId for update", nativeQuery = true)
    Optional<Follow> findByFollowerIdAndFollowingId(@Param("followerId") String followerId, @Param("followingId") String followingId);
}
