package com.team.synergy.apply.dto;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.dto.MemberDto;
import com.team.synergy.project.dto.ProjectDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter @Setter
public class ApplyDto {
    private LocalDateTime applyTime;
    private MemberDto memberDto;
    private ProjectDto projectDto;

    @Builder
    public ApplyDto(LocalDateTime applyTime, MemberDto memberDto, ProjectDto projectDto){
        this.applyTime = applyTime;
        this.memberDto = memberDto;
        this.projectDto = projectDto;
    }

    public static ApplyDto from(Apply apply){
        return ApplyDto.builder()
                .memberDto(MemberDto.from(apply.getMember()))
                .projectDto(ProjectDto.from(apply.getProject()))
                .build();
    }

    public static List<ApplyDto> of(List<Apply> applys){
        return applys.stream().map(apply -> ApplyDto.from(apply)).collect(Collectors.toList());
    }
}
