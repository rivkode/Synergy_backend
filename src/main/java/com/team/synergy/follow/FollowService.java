package com.team.synergy.follow;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.follow.dto.request.CreateFollowRequest;
import com.team.synergy.follow.dto.response.CreateFollowResponse;
import com.team.synergy.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public CreateFollowResponse createFollow(Member follower, Member following, CreateFollowRequest request) {
        List<String> followingIdList = followRepository.findFollowingIdsByFollowerId(request.getFollowerId());
        if (followingIdList.contains(request.getFollowingId())) {
            // 아무것도 수행하지 않음
            return null;
        }

        Follow savedFollow = followSave(follower, following);

        return CreateFollowResponse.from(savedFollow);
    }

    @Transactional
    public synchronized Follow followSave(Member follower, Member following) {
        Optional<Follow> followOptional = followRepository.findByFollowerIdAndFollowingId(follower.getId(), following.getId());

        if (followOptional.isPresent()) {
            return null;
        } else {
            Follow follow = Follow.createFollow(follower, following, FollowStatus.FOLLOW);
            return this.followRepository.save(follow);
        }
    }

    @Transactional
    public void deleteFollow(Member follower, Member following) {
        Follow follow = followRepository.findByFollowerIdAndFollowingId(follower.getId(), following.getId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "유효하지 않은 follow 입니다"));
        follow.cancelFollow();
    }
}
