package com.team.synergy.project;

import com.team.synergy.BaseTime;
import com.team.synergy.apply.Apply;
import com.team.synergy.projectlike.ProjectLike;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(
            mappedBy = "project"
    )
    private List<ProjectLike> likes = new ArrayList<>();

    @OneToMany(
            mappedBy = "project"
    )
    private List<Apply> applyList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus; // PROCESS, DONE

    public Project(String name, String content, String field) {
        this.name = name;
        this.content = content;
        this.field = field;
    }
}
