package com.team.synergy.projectlike;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProjectLikeService {

    private final ProjectLikeRepository projectLikeRepository;

    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    @Transactional
    public Long createProjectLike(Member member, Project project) {
        ProjectLike projectLike = ProjectLike.createProjectLike(member, project);

        projectLikeRepository.save(projectLike);
        return projectLike.getId();
    }

    @Transactional
    public void deleteProjectLike(Member member, Project project) {
        ProjectLike projectLike = projectLikeRepository.findByMemberAndProject(member, project)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "존재하지 않는 Project 입니다"));
        projectLike.cancelProjectLike();

    }
}
