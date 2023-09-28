package com.team.synergy.projectlike;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectService;
import com.team.synergy.projectlike.dto.request.ProjectLikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectLikeController {
    private final MemberService memberService;
    private final ProjectLikeService projectLikeService;
    private final ProjectService projectService;

    @PutMapping("/{projectId}/like")
    public ResponseEntity<Void> updateProjectLike(HttpServletRequest servletRequest, @PathVariable Long projectId, @RequestBody ProjectLikeType type) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        Project project = projectService.findProjectById(projectId);

        projectLikeService.updateProjectLike(member, project, type);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
