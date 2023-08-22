package com.team.synergy.projectlike;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> createProjectLike(@PathVariable Long projectId, @RequestParam("memberId") String memberId) {
        Member member = memberService.findMemberById(memberId);
        Project project = projectService.findProjectById(projectId);
        projectLikeService.createProjectLike(member, project);
        return ResponseEntity.ok().body("좋아요 성공");
    }

    @DeleteMapping("/{projectId}/likes")
    public ResponseEntity<String> deleteProjectLike(@PathVariable Long projectId, @RequestParam("memberId") String memberId) {
        Member member = memberService.findMemberById(memberId);
        Project project = projectService.findProjectById(projectId);
        projectLikeService.deleteProjectLike(member, project);
        return ResponseEntity.ok().body("좋아요 취소 성공");
    }
}
