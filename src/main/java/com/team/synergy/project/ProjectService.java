package com.team.synergy.project;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


    public void projectCreate(String name, String content, String field, LocalDate createDate, LocalDate endDate) {
        Project project = Project.builder()
                .name(name)
                .content(content)
                .field(field)
                .createDate(createDate)
                .endDate(endDate)
                .build();

        projectRepository.save(project);
    }

    public Project findById(Long id) {
        Optional<Project> project = this.projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "프로젝트가 없습니다.");
        }
    }

    public void projectDelete(Project project) {
        this.projectRepository.delete(project);
    }
}
