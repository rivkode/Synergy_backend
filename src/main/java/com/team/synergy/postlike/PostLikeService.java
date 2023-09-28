package com.team.synergy.postlike;

import com.team.synergy.member.Member;
import com.team.synergy.post.Post;
import com.team.synergy.postlike.dto.request.PostLikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void updatePostLike(Member member, Post post, PostLikeType type) {

        PostLikeStatus status;
        if (type.getLikeType().equals("like")) {
            status = PostLikeStatus.POST_LIKE;
        } else {
            status = PostLikeStatus.POST_UNLIKE;
        }


        // 이전에 member가 동일한 post에 대해 post_like를 가지고 있는지 check
        Optional<PostLike> postLikeOptional = postLikeRepository.findPostLikeByMemberIdAndPostId(member.getId(), post.getId());
        if (postLikeOptional.isPresent()) {
            postLikeOptional.get().setStatus(status);
        } else {
            PostLike postLike = new PostLike(member, post);
            postLikeRepository.save(postLike);
        }
    }

    public List<Long> getPostIdsByMemberId(String memberId) {
        List<Long> postIds = postLikeRepository.findPostIdsByMemberId(memberId);

        return postIds;
    }
}
