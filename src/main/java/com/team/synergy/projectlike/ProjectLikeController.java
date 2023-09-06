package com.team.synergy.projectlike;

import com.team.synergy.member.MemberService;
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

    @PutMapping("/{projectId}/like")
    public ResponseEntity<Void> updateProjectLike(HttpServletRequest servletRequest, @PathVariable Long projectId, @RequestBody ProjectLikeType type) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        projectLikeService.updateProjectLike(memberId, projectId, type);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
