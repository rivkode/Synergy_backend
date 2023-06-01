package com.team.synergy.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class ProjectLikeController {

    private final ProjectLikeService projectLikeService;

    @PostMapping("/create")
    public ResponseEntity<String> like(@RequestParam("memberId") Long memberId,
                                       @RequestParam("projectId") Long projectId) {
        projectLikeService.like(memberId, projectId);
        return ResponseEntity.ok().body("좋아요 성공");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelProjectLike(@RequestParam("projectLikeId") Long projectLikeId) {
        projectLikeService.cancelProjectLike(projectLikeId);
        return ResponseEntity.ok().body("좋아요 취소 성공");
    }
}
