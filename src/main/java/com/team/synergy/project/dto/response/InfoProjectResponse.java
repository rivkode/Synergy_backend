package com.team.synergy.project.dto.response;

import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class InfoProjectResponse {
    private Long id;
    private String name;
    private String content;
    private String field;
    private String leaderId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int likes;
    private List<String> teamMemberIds;


    public static InfoProjectResponse from(Project project, List<String> projectMemberIds) {

        return new InfoProjectResponse(
                project.getId(), project.getName(), project.getContent(), project.getField(),
                project.getLeaderId() , project.getStartAt(), project.getEndAt()
                , project.getLikes().size(), projectMemberIds
        );
    }

}
