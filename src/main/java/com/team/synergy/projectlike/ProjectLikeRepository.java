package com.team.synergy.projectlike;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import com.team.synergy.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
    Optional<ProjectLike> findByMemberAndProject(Member member, Project project);
}
