package com.team.synergy.project;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.project.dto.ProjectDto;
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
    public void projectCreate(ProjectDto projectDto) {
        Project project = Project.builder()
                .name(projectDto.getName())
                .content(projectDto.getContent())
                .field(projectDto.getField())
                .createDate(projectDto.getCreateDate())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .projectStatus(ProjectStatus.PROCESS)
                .build();

        projectRepository.save(project);
    }

    public Project findProjectById(Long id) {
        Optional<Project> project = this.projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "프로젝트가 없습니다.");
        }
    }

    public List<ProjectDto> findAll() {
        return ProjectDto.fromList(projectRepository.findAll());
    }

    @Transactional
    public void projectDelete(Long projectId) {
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
}
