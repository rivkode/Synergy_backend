package com.team.synergy.project.dto.response;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectStatus;
import com.team.synergy.projectlike.ProjectLike;
import com.team.synergy.projectmember.ProjectMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Builder
public class ProjectGetResponse {
    private Long id;
    private String name;
    private String content;
    private String field;
    private int likes;
    private int projectMembers;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private ProjectStatus projectStatus;

    public static Page<ProjectGetResponse> toResponse(Page<Project> projects) {
        Page<ProjectGetResponse> projectGetResponses = projects.map(m -> ProjectGetResponse.builder()
                .id(m.getId())
                .name(m.getName())
                .content(m.getContent())
                .field(m.getField())
                .likes(m.getLikes().size())
                .projectMembers(m.getProjectMembers().size())
                .createAt(m.getCreateAt())
                .updateAt(m.getUpdateAt())
                .startAt(m.getStartAt())
                .endAt(m.getEndAt())
                .projectStatus(m.getProjectStatus())
                .build());
        return projectGetResponses;
    }
}
