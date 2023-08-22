package com.team.synergy.apply;

import com.team.synergy.apply.dto.ApplyDto;
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
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long apply(String memberId, Long projectId) {
        Optional<Member> memberValue = memberRepository.findById(memberId);
        Member member = memberValue.orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "멤버가 없습니다."));


        Optional<Project> projectValue = projectRepository.findById(projectId);
        Project project = projectValue.orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "프로젝트가 없습니다."));


        Apply apply = Apply.createApply(member, project);

        applyRepository.save(apply);
        return apply.getId();
    }

    @Transactional
    public void cancelApply(Long applyId) {
        Optional<Apply> applyValue = applyRepository.findById(applyId);
        Apply apply = applyValue.orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "해당 신청은 없습니다."));

        apply.cancel();
    }

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

    public List<ApplyDto> getApplyByMemberId(String memberId) {
        Optional<Member> memberValue = memberRepository.findById(memberId);
        Member member = memberValue.orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "멤버가 없습니다."));

        List<Apply> applyList = applyRepository.findAppliesByMember(member);
        return ApplyDto.of(applyList);
    }
}
