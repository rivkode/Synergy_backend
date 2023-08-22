package com.team.synergy.project;

import com.team.synergy.apply.Apply;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter @Setter
public class Project {
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

    @Column(name = "createDate")
    private LocalDateTime createDate;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus; // PROCESS, DONE


//    Set<Member> voter;
}
