package com.team.synergy.projectlike;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import com.team.synergy.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
    Optional<ProjectLike> findByMemberAndProject(Member member, Project project);

    Optional<ProjectLike> findByMemberIdAndProjectId(String memberId, Long projectId);

    @Query(value = "SELECT project_id FROM project_like WHERE member_id = :memberId AND project_like_status = 'PROJECT_LIKE'", nativeQuery = true)
    List<Long> findProjectIdsByMemberId(@Param("memberId") String memberId);
}
