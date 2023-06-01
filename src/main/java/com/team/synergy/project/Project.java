package com.team.synergy.project;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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

    @OneToOne(mappedBy = "project")
    private Apply apply;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_content")
    private String content;

    @Column(name = "project_field")
    private String field;

    @Column(name = "createDate")
    private LocalDateTime createDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus; // PROCESS, DONE


//    Set<Member> voter;
}
