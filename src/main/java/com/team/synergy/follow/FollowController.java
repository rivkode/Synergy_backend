package com.team.synergy.follow;

import com.team.synergy.follow.dto.request.FollowType;
import com.team.synergy.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final MemberService memberService;

    @PutMapping("/{followerId}")
    public ResponseEntity<Void> updateFollow(HttpServletRequest servletRequest, @PathVariable("followerId") String followerId, @RequestBody FollowType type) {
        String followingId = memberService.findMemberIdByToken(servletRequest);
        followService.updateFollow(followerId, followingId, type);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
