package com.team.synergy.postlike;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.Post;
import com.team.synergy.post.PostService;
import com.team.synergy.postlike.dto.request.PostLikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public void updatePostLike(String memberId, Long postId, PostLikeType type) {

        PostLikeStatus status;
        if (type.getLikeType().equals("like")) {
            status = PostLikeStatus.LIKE;
        } else {
            status = PostLikeStatus.UNLIKE;
        }


        // 이전에 member가 동일한 post에 대해 post_like를 가지고 있는지 check
        Optional<PostLike> postLikeOptional = postLikeRepository.findPostLikeByMemberIdAndPostId(memberId, postId);
        if (postLikeOptional.isPresent()) {
            postLikeOptional.get().setStatus(status);
        } else {
            Member member = memberService.findMemberById(memberId);
            Post post = postService.findPostById(postId);
            PostLike postLike = new PostLike(member, post);
            postLikeRepository.save(postLike);
        }
    }
}
