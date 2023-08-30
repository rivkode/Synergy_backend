package com.team.synergy.projectlike;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectRepository;
import com.team.synergy.projectlike.dto.request.CreateProjectLikeRequest;
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

    @Transactional
    public CreateProjectLikeResponse createProjectLike(Member member, Project project, CreateProjectLikeRequest request) {
        // member와 project에 대해 동일한 project_like가 있는지 check
        Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByMemberAndProject(member, project);
        if (projectLikeOptional.isPresent()) {
            throw new AppException(ErrorCode.INVALID_DATA, "이미 projectLike가 존재합니다");
        } else {
            ProjectLike projectLike = request.toEntity(member, project);
            ProjectLike savedProjectLike = projectLikeRepository.save(projectLike);
            return CreateProjectLikeResponse.from(savedProjectLike);
        }
    }

    @Transactional
    public DeleteProjectLikeResponse deleteProjectLike(Member member, Project project) {
        ProjectLike projectLike = projectLikeRepository.findByMemberAndProject(member, project)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "존재하지 않는 Project 입니다"));
        projectLike.cancelProjectLike();
        projectLikeRepository.delete(projectLike);
        return DeleteProjectLikeResponse.from(projectLike);
    }
}
