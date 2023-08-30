package com.team.synergy.apply;

import com.team.synergy.apply.dto.request.CreateApplyRequest;
import com.team.synergy.apply.dto.request.DeleteApplyRequest;
import com.team.synergy.apply.dto.response.CreateApplyResponse;
import com.team.synergy.apply.dto.response.ListMyApplyResponse;
import com.team.synergy.generic.Result;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;
    private final MemberService memberService;
    private final ProjectService projectService;

    @PostMapping()
    public ResponseEntity<CreateApplyResponse> apply(@RequestBody CreateApplyRequest request) {
        Member member = memberService.findMemberById(request.getMemberId());
        Project project = projectService.findProjectById(request.getProjectId());
        return ResponseEntity.status(HttpStatus.CREATED).body(applyService.createApply(member, project, request));
    }

    @DeleteMapping()
    public ResponseEntity<Void> cancelApply(@RequestBody DeleteApplyRequest request) {
        Member member = memberService.findMemberById(request.getMemberId());
        Project project = projectService.findProjectById(request.getProjectId());

        applyService.cancelApply(member, project);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ListMyApplyResponse> listMyProject(@PathVariable("id") String memberId) {
        Member member = memberService.findMemberById(memberId);


        return ResponseEntity.ok()
                .body(applyService.listMyApply(member));
    }
}
