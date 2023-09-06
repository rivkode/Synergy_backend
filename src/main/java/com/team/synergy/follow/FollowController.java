package com.team.synergy.follow;

import com.team.synergy.follow.dto.request.FollowType;
import com.team.synergy.follow.dto.response.InfoFollowResponse;
import com.team.synergy.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final MemberService memberService;

    @PutMapping("/{followingId}")
    public ResponseEntity<Void> updateFollow(HttpServletRequest servletRequest, @PathVariable("followingId") String followingId, @RequestBody FollowType type) {
        String followerId = memberService.findMemberIdByToken(servletRequest);
        followService.updateFollow(followerId, followingId, type);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<InfoFollowResponse> getFollowWithProfileId(HttpServletRequest servletRequest, @PathVariable("memberId") String memberId) {

        return ResponseEntity.ok()
                .body(followService.findInfoFollowByMemberId(memberId));
    }
}
