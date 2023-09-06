package com.team.synergy.projectmember;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;



    @Transactional
    public void createProjectMember(Project project, Member member) {
        Optional<ProjectMember> projectMemberOptional = projectMemberRepository.findByProjectAndMember(project, member);
        if (projectMemberOptional.isPresent()) {
            throw new AppException(ErrorCode.INVALID_DATA, "이미 projectMember가 존재합니다");
        } else {
            project.setLeaderId(member.getId());

            ProjectMember projectMember = new ProjectMember(project, member);
            project.getProjectMembers().add(projectMember);
            projectMemberRepository.save(projectMember);
        }
    }

    @Transactional
    public void deleteProjectMember(Project project, Member member) {
        ProjectMember projectMember = projectMemberRepository.findByProjectAndMember(project, member)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "projectMember가 없습니다"));
        projectMemberRepository.delete(projectMember);
    }

    @Transactional
    public List<String> getProjectMemberIds(Long projectId) {
        return projectMemberRepository.findMemberIdsByProjectId(projectId);
    }

    public List<Long> findProjectIdsByMemberId(String memberId) {
        return projectMemberRepository.findProjectIdsByMemberId(memberId);
    }
}
