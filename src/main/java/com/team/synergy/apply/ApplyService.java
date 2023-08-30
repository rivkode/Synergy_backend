package com.team.synergy.apply;

import com.team.synergy.apply.dto.ApplyDto;
import com.team.synergy.apply.dto.request.CreateApplyRequest;
import com.team.synergy.apply.dto.response.CreateApplyResponse;
import com.team.synergy.apply.dto.response.ListMyApplyResponse;
import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.dto.MemberDto;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.Project;
import com.team.synergy.project.dto.ProjectDto;
import com.team.synergy.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplyService {

    private final ApplyRepository applyRepository;

    @Transactional
    public CreateApplyResponse createApply(Member member, Project project, CreateApplyRequest request) {

        Apply apply = request.toEntity(member, project);
        Apply savedApply = applyRepository.save(apply);

        return CreateApplyResponse.from(savedApply);
    }

    @Transactional
    public void cancelApply(Member member, Project project) {
        Apply apply = applyRepository.findApplyByMemberAndProject(member, project)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "Apply가 존재하지 않습니다"));

        apply.cancel();
    }

    @Transactional
    public ListMyApplyResponse listMyApply(Member member) {

        List<Apply> applyList = applyRepository.findAppliesByMember(member);
        List<ApplyDto> applyDtoList = ApplyDto.of(applyList);
        return ListMyApplyResponse.from(applyDtoList);
    }
}
