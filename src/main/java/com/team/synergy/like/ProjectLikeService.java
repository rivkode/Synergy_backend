package com.team.synergy.like;

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
    public Long like(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).get();
        Project project = projectRepository.findById(projectId).get();

        ProjectLike projectLike = ProjectLike.createProjectLike(member, project);

        projectLikeRepository.save(projectLike);
        return projectLike.getId();
    }

    @Transactional
    public void cancelProjectLike(Long projectLikeId) {
        ProjectLike projectLike = projectLikeRepository.findById(projectLikeId).get();
        projectLike.cancelProjectLike();

    }
}
