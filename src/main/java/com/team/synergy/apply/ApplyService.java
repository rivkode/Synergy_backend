package com.team.synergy.apply;

import com.team.synergy.apply.dto.response.CreateApplyResponse;
import com.team.synergy.apply.dto.response.MemberIdsResponse;
import com.team.synergy.apply.dto.response.ProjectIdsResponse;
import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplyService {

    private final ApplyRepository applyRepository;

    @Transactional
    public CreateApplyResponse createApply(Member member, Project project) {
        Optional<Apply> applyOptional = applyRepository.findApplyByMemberAndProject(member, project);
        if (applyOptional.isPresent()) {
            throw new AppException(ErrorCode.INVALID_DATA, "이미 apply가 존재합니다");
        } else {
            Apply apply = new Apply(member, project);
            Apply savedApply = applyRepository.save(apply);
            return CreateApplyResponse.from(savedApply);
        }
    }

    @Transactional
    public void deleteApply(Member member, Project project) {
        Apply apply = applyRepository.findApplyByMemberAndProject(member, project)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "Apply가 존재하지 않습니다"));

        applyRepository.delete(apply);
    }

    @Transactional
    public MemberIdsResponse findMemberIdsByProject(Member member) {
        List<Long> projectIds = applyRepository.findProjectIdsByMemberId(member.getId());

        return MemberIdsResponse.createWithProjectIds(projectIds);
    }

    @Transactional
    public ProjectIdsResponse findProjectIdsByMemberId(Project project) {
        List<String> memberIds = applyRepository.findProjectIdsByMemberId(project.getId());

        return ProjectIdsResponse.createWithMemberIds(memberIds);
    }

    @Transactional
    public void accept(Project project, Member member) {
        Apply apply = applyRepository.findApplyByMemberAndProject(member, project)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "Apply가 존재하지 않습니다"));
        apply.setStatus(ApplyStatus.DONE);
        applyRepository.save(apply);
    }

    public void reject(Project project, Member member) {
        Apply apply = applyRepository.findApplyByMemberAndProject(member, project)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "Apply가 존재하지 않습니다"));
        apply.setStatus(ApplyStatus.REJECT);
    }
}
