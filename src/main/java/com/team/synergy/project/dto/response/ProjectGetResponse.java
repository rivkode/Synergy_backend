package com.team.synergy.project.dto.response;

import com.team.synergy.apply.Apply;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class ProjectGetResponse {
    private Long id;
    private Apply apply;
    private String name;
    private String content;
    private String field;
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectStatus projectStatus;

    public static Page<ProjectGetResponse> toResponse(Page<Project> projects) {
        Page<ProjectGetResponse> projectGetResponses = projects.map(m -> ProjectGetResponse.builder()
                .id(m.getId())
                .name(m.getName())
                .content(m.getContent())
                .field(m.getField())
                .createDate(m.getCreateDate())
                .startDate(m.getStartDate())
                .endDate(m.getEndDate())
                .projectStatus(m.getProjectStatus())
                .build());
        return projectGetResponses;
    }
}
