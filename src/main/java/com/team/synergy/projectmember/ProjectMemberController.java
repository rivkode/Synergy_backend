package com.team.synergy.projectmember;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectService;
import com.team.synergy.project.dto.request.ProjectMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;
    private final MemberService memberService;
    private final ProjectService projectService;

    @PostMapping("/{projectId}")
    public ResponseEntity<Void> createProjectMember(HttpServletRequest servletRequest, @PathVariable("projectId") Long projectId) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        Project project = projectService.findProjectById(projectId);

        projectMemberService.createProjectMember(project, member);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProjectMember(HttpServletRequest servletRequest, @PathVariable("projectId") Long projectId) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        Project project = projectService.findProjectById(projectId);
        projectMemberService.deleteProjectMember(project, member);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
