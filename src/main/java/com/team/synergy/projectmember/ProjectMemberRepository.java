package com.team.synergy.projectmember;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    Optional<ProjectMember> findByProjectAndMember(Project project, Member member);

    @Query(value = "SELECT pm.member_id FROM project_member pm WHERE pm.project_id = :projectId", nativeQuery = true)
    List<String> findMemberIdsByProjectId(@Param("projectId") Long projectId);
}
