package com.team.synergy.projectlike;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectService;
import com.team.synergy.projectlike.dto.request.CreateProjectLikeRequest;
import com.team.synergy.projectlike.dto.request.DeleteProjectLikeRequest;
import com.team.synergy.projectlike.dto.response.CreateProjectLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectLikeController {
    private final MemberService memberService;
    private final ProjectService projectService;
    private final ProjectLikeService projectLikeService;

    @PostMapping("/{projectId}/likes")
    public ResponseEntity<CreateProjectLikeResponse> createProjectLike(@PathVariable Long projectId, @RequestBody CreateProjectLikeRequest request) {
        Member member = memberService.findMemberById(request.getMemberId());
        Project project = projectService.findProjectById(projectId);

        return ResponseEntity.ok().body(projectLikeService.createProjectLike(member, project, request));
    }

    @DeleteMapping("/{projectId}/likes")
    public ResponseEntity<Void> deleteProjectLike(@PathVariable Long projectId, @RequestBody DeleteProjectLikeRequest request) {
        Member member = memberService.findMemberById(request.getMemberId());
        Project project = projectService.findProjectById(projectId);
        projectLikeService.deleteProjectLike(member, project);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
