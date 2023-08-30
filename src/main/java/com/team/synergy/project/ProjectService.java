package com.team.synergy.project;

import com.team.synergy.apply.Apply;
import com.team.synergy.apply.ApplyService;
import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.project.dto.ProjectDto;
import com.team.synergy.project.dto.request.CreateProjectRequest;
import com.team.synergy.project.dto.response.CreateProjectResponse;
import com.team.synergy.project.dto.response.InfoProjectResponse;
import com.team.synergy.project.dto.response.ProjectGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


    @Transactional
    public CreateProjectResponse createProject(Member member, CreateProjectRequest request) {
        Project savedProject = projectRepository.save(request.toEntity());
        Apply apply = Apply.createApply(member, savedProject);
        savedProject.getApplyList().add(apply);

        return CreateProjectResponse.from(savedProject);

    }

    public Project findProjectById(Long id) {
        Optional<Project> project = this.projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "프로젝트가 없습니다.");
        }
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        this.projectRepository.delete(project);
    }

    public List<ProjectDto> findByKeyword(String keyword) {
        System.out.println("keyword = " + keyword);
        List<Project> search = projectRepository.search(keyword);
        System.out.println("search size = " + search.size());

        return ProjectDto.fromList(search);
    }

    public Page<ProjectGetResponse> getProjects(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        Page<ProjectGetResponse> projectGetResponses = ProjectGetResponse.toResponse(projects);

        return projectGetResponses;
    }

    public Page<ProjectGetResponse> searchProjects(Pageable pageable, String keyword) {
        Page<Project> projects = projectRepository.findByNameContaining(keyword, pageable);
        Page<ProjectGetResponse> projectGetResponses = ProjectGetResponse.toResponse(projects);

        return projectGetResponses;


    }

    public InfoProjectResponse projectInfo(Long projectId) {
        Project project = findProjectById(projectId);
        return InfoProjectResponse.from(project);
    }
}
