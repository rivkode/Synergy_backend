package com.team.synergy.projectlike;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.Post;
import com.team.synergy.post.PostService;
import com.team.synergy.postlike.dto.request.PostLikeType;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectRepository;
import com.team.synergy.project.ProjectService;
import com.team.synergy.projectlike.dto.request.CreateProjectLikeRequest;
import com.team.synergy.projectlike.dto.request.ProjectLikeType;
import com.team.synergy.projectlike.dto.response.CreateProjectLikeResponse;
import com.team.synergy.projectlike.dto.response.DeleteProjectLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectLikeService {

    private final ProjectLikeRepository projectLikeRepository;
    private final MemberService memberService;
    private final ProjectService projectService;

    @Transactional
    public void updateProjectLike(String memberId, Long projectId, ProjectLikeType type) {
        ProjectLikeStatus status;

        if (type.getLikeType().equals("like")) {
            status = ProjectLikeStatus.LIKE;
        } else {
            status = ProjectLikeStatus.UNLIKE;
        }

        // member와 project에 대해 동일한 project_like가 있는지 check
        Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByMemberIdAndProjectId(memberId, projectId);
        if (projectLikeOptional.isPresent()) {
            projectLikeOptional.get().setStatus(status);
        } else {
            Member member = memberService.findMemberById(memberId);
            Project project = projectService.findProjectById(projectId);
            ProjectLike projectLike = new ProjectLike(member, project);
            projectLikeRepository.save(projectLike);

        }
    }
}
