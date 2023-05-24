package com.team.synergy.apply;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    public Long apply(Long memberId, Long projectId) {
        Member member = memberRepository.findById(memberId).get();
        Project project = projectRepository.findById(projectId).get();

        Apply apply = Apply.createApply(member, project);

        applyRepository.save(apply);
        return apply.getId();
    }

    public Apply findById(Long id) {
        Optional<Apply> apply = this.applyRepository.findById(id);
        if (apply.isPresent()) {
            return apply.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "신청된 내역이 없습니다.");
        }
    }
}
