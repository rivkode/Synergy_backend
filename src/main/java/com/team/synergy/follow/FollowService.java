package com.team.synergy.follow;

import com.team.synergy.follow.dto.request.FollowType;
import com.team.synergy.follow.dto.response.InfoFollowResponse;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.notification.NotificationService;
import com.team.synergy.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberService memberService;
    private final NotificationService notificationService;

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
            notificationService.send(following, NotificationType.FOLLOW, followerId);
        }
    }

    @Transactional
    public InfoFollowResponse findInfoFollowByMemberId(String memberId) {
        List<String> followers = followRepository.findFollowerIdsByFollowingId(memberId);
        List<String> followings = followRepository.findFollowingIdsByFollowerId(memberId);

        return InfoFollowResponse.from(followers, followings);
    }

    public List<String> findFollowingIdsByMemberId(String memberId) {
        return followRepository.findFollowingIdsByFollowerId(memberId);
    }
}
