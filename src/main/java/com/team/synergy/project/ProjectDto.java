package com.team.synergy.project;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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

    private LocalDateTime endDate;

    @Builder
    public ProjectDto(Long id, String name, String content, String field, LocalDateTime createDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.field = field;
        this.createDate = createDate;
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
