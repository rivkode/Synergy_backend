package com.team.synergy.apply;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberDto;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectDto;
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
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long apply(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).get();
        Project project = projectRepository.findById(projectId).get();

        Apply apply = Apply.createApply(member, project);

        applyRepository.save(apply);
        return apply.getId();
    }

//    public Long cancel(Long applyId) {
//
//    }

    public Apply findById(Long id) {
        Optional<Apply> apply = applyRepository.findById(id);
        if (apply.isPresent()) {
            return apply.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "신청된 내역이 없습니다.");
        }
    }

    public ApplyDto getApplyDto(Long id) {
        Apply apply = findById(id);
        LocalDateTime current = LocalDateTime.now();
        return ApplyDto.builder()
                .applyTime(current)
                .memberDto(MemberDto.from(apply.getMember()))
                .projectDto(ProjectDto.from(apply.getProject()))
                .build();
    }

    public List<ApplyDto> getApplyByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        List<Apply> applyList = applyRepository.findAppliesByMember(member);
        return ApplyDto.of(applyList);
    }
}