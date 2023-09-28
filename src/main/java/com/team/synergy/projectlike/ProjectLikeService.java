package com.team.synergy.projectlike;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import com.team.synergy.projectlike.dto.request.ProjectLikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectLikeService {

    private final ProjectLikeRepository projectLikeRepository;

    @Transactional
    public void updateProjectLike(Member member, Project project, ProjectLikeType type) {
        ProjectLikeStatus status;

        if (type.getLikeType().equals("like")) {
            status = ProjectLikeStatus.PROJECT_LIKE;
        } else {
            status = ProjectLikeStatus.PROJECT_UNLIKE;
        }

        // member와 project에 대해 동일한 project_like가 있는지 check
        Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByMemberIdAndProjectId(member.getId(), project.getId());
        if (projectLikeOptional.isPresent()) {
            projectLikeOptional.get().setStatus(status);
        } else {
            ProjectLike projectLike = new ProjectLike(member, project);
            projectLikeRepository.save(projectLike);

        }
    }

    public List<Long> getProjectIdsByMemberId(String memberId) {
        List<Long> projectIds = projectLikeRepository.findProjectIdsByMemberId(memberId);

        return projectIds;

    }
}
