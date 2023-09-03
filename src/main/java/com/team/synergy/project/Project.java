package com.team.synergy.project;

import com.team.synergy.BaseTime;
import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import com.team.synergy.projectlike.ProjectLike;
import com.team.synergy.projectmember.ProjectMember;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter @Setter
public class Project extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_content")
    private String content;

    @Column(name = "project_field")
    private String field;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @OneToMany(
            mappedBy = "project"
    )
    private List<ProjectLike> likes = new ArrayList<>();

    @OneToMany(
            mappedBy = "project"
    )
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(
            mappedBy = "project"
    )
    private List<Apply> applys = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus; // PROCESS, CANCEL, DONE

    private String leaderId;

    public Project(String name, String content, String field, LocalDateTime startAt, LocalDateTime endAt) {
        this.name = name;
        this.content = content;
        this.field = field;
        this.startAt = startAt;
        this.endAt = endAt;
        this.projectStatus = ProjectStatus.PROCESS;
    }
}
