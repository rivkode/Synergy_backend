package com.team.synergy.apply;

import com.team.synergy.apply.dto.request.CreateApplyRequest;
import com.team.synergy.apply.dto.request.DeleteApplyRequest;
import com.team.synergy.apply.dto.response.CreateApplyResponse;
import com.team.synergy.apply.dto.response.MemberIdsResponse;
import com.team.synergy.apply.dto.response.ProjectIdsResponse;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectService;
import com.team.synergy.projectmember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;
    private final MemberService memberService;
    private final ProjectService projectService;

    private final ProjectMemberService projectMemberService;

    @PostMapping("/{projectId}")
    public ResponseEntity<CreateApplyResponse> createApply(HttpServletRequest servletRequest, @PathVariable("projectId") Long projectId) {
        Member member = memberService.findMemberByToken(servletRequest);
        Project project = projectService.findProjectById(projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(applyService.createApply(member, project));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteApply(HttpServletRequest servletRequest, @PathVariable("projectId") Long projectId) {
        Member member = memberService.findMemberByToken(servletRequest);
        Project project = projectService.findProjectById(projectId);

        applyService.deleteApply(member, project);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // leader가 요청된 apply에 대해서 승락
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptApply(@RequestBody CreateApplyRequest request) {
        Member member = memberService.findMemberById(request.getMemberId());
        Project project = projectService.findProjectById(request.getProjectId());
        projectMemberService.createProjectMember(project, member);
        applyService.accept(project, member);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // leader가 요청된 apply에 대해 거절
    @DeleteMapping("/reject")
    public ResponseEntity<Void> rejectApply(@RequestBody DeleteApplyRequest request) {
        Member member = memberService.findMemberById(request.getMemberId());
        Project project = projectService.findProjectById(request.getProjectId());
        applyService.reject(project, member);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 내가 신청한 applyProjectList 가져오기
    @GetMapping("/me")
    public ResponseEntity<MemberIdsResponse> listApplyByMember(HttpServletRequest servletRequest) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);

        return ResponseEntity.ok()
                .body(applyService.findMemberIdsByProject(member));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectIdsResponse> listApplyByProject(@PathVariable("projectId") Long projectId) {
        Project project = projectService.findProjectById(projectId);

        return ResponseEntity.ok()
                .body(applyService.findProjectIdsByMemberId(project));
    }
}
