package com.team.synergy.project.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CreateProjectRequest {
    private String name;
    private String content;
    private String field;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime startAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime endAt;

    public Project toEntity() {
        return new Project(this.name, this.content, this.field, this.startAt, this.endAt);
    }

}
