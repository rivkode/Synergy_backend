package com.team.synergy.project.dto;

import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectDto {
    private Long id;
    private String name;

    private String content;

    private String field;

    private LocalDateTime createDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public ProjectDto(Long id, String name, String content, String field, LocalDateTime createDate, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.field = field;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public static ProjectDto from(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .content(project.getContent())
                .field(project.getField())
                .createDate(project.getCreateDate())
                .endDate(project.getEndDate())
                .build();
    }

    public static List<ProjectDto> fromList(List<Project> projectList) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for(Project project : projectList) {
            projectDtoList.add(ProjectDto.from(project));
        }
        return projectDtoList;
    }

}
