package com.team.synergy.follow;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.follow.dto.request.FollowType;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberService memberService;

    @Transactional
    public void updateFollow(String followerId, String followingId, FollowType type) {
        FollowStatus status;

        if (type.getFollowType().equals("follow")) {
            status = FollowStatus.FOLLOW;
        } else {
            status = FollowStatus.UNFOLLOW;
        }

        Optional<Follow> followOptional = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (followOptional.isPresent()) {
            followOptional.get().setStatus(status);
        } else {
            Member follower = memberService.findMemberById(followerId);
            Member following = memberService.findMemberById(followingId);
            Follow follow = new Follow(follower, following);
            followRepository.save(follow);
        }
    }

    @Transactional
    public synchronized Follow followSave(Follow follow) {
        Optional<Follow> followOptional = followRepository.findById(follow.getId());

        if (followOptional.isPresent()) {
            return null;
        } else {
            return this.followRepository.save(follow);
        }
    }

    @Transactional
    public void cancelFollow(Member follower, Member following) {
        Follow followOptional = followRepository.findByFollowerIdAndFollowingId(follower.getId(), following.getId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "존재하지 않는 Follow 입니다"));

        followOptional.cancelFollow();
    }
}
