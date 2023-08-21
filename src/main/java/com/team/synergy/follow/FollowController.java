package com.team.synergy.follow;

import com.team.synergy.follow.dto.request.CreateFollowRequest;
import com.team.synergy.follow.dto.response.CreateFollowResponse;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final MemberService memberService;

    @RequestMapping
    public ResponseEntity<CreateFollowResponse> createFollow(@RequestBody CreateFollowRequest request) {
        Member follower = memberService.findMemberById(request.getFollowerId());
        Member following = memberService.findMemberById(request.getFollowingId());

        CreateFollowResponse createFollowResponse = followService.createFollow(follower, following, request);
        if (createFollowResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CreateFollowResponse.from(null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createFollowResponse);
    }

    @DeleteMapping("/{followingId}")
    public ResponseEntity<Void> deleteFollow(@PathVariable String followingId) {
        // login 기능 추가해야함
        String followerId = "1";
        Member following = memberService.findMemberById(followingId);
        Member follower = memberService.findMemberById(followerId);
        followService.deleteFollow(follower, following);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
