package com.team.synergy.apply;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

    List<Apply> findAppliesByMember(Member member);

    Optional<Apply> findApplyByMemberAndProject(Member member, Project project);

    // 마이 페이지 신청한 project
    @Query(value = "SELECT a.project_id FROM apply a WHERE a.member_id = :memberId", nativeQuery = true)
    List<Long> findProjectIdsByMemberId(String memberId);

    // project 별 memberId
    @Query(value = "SELECT a.member_id FROM apply a WHERE a.project_id = :projectId", nativeQuery = true)
    List<String> findProjectIdsByMemberId(Long projectId);
}
