package com.team.synergy.follow;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.follow.dto.request.CreateFollowRequest;
import com.team.synergy.follow.dto.response.CreateFollowResponse;
import com.team.synergy.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
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

        Follow follow = request.toEntity(follower, following);
        Follow savedFollow = followSave(follow);

        return CreateFollowResponse.from(savedFollow);
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
