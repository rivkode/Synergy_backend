package com.team.synergy.apply;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class ApplyDto {
    private LocalDateTime applyTime;
    private Member member;
    private Project project;
}
