package com.team.synergy.project;

import com.team.synergy.member.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProjectDto {
    private String name;

    private String content;

    private String field;

    private LocalDate createDate;

    private LocalDate endDate;

    private List<Member> teamMembers;

    private List<Member> appliers;
}
