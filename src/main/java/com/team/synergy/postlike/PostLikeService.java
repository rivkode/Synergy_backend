package com.team.synergy.postlike;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.post.Post;
import com.team.synergy.post.PostRepository;
import com.team.synergy.postlike.dto.request.CreatePostLikeRequest;
import com.team.synergy.postlike.dto.response.CreatePostLikeResponse;
import com.team.synergy.postlike.dto.response.DeletePostLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public CreatePostLikeResponse createPostLike(Member member, Post post, CreatePostLikeRequest request) {
        PostLike postLike = request.toEntity(member, post);
        PostLike savedPostLike = postLikeRepository.save(postLike);

        return CreatePostLikeResponse.from(savedPostLike);
    }

    @Transactional
    public DeletePostLikeResponse deletePostLike(Member member, Post post) {
        PostLike postLike = postLikeRepository.findPostLikeByMemberAndPost(member, post)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "유효하지 않은 PostLike입니다"));
        postLike.deletePostLike();
        return DeletePostLikeResponse.from(postLike); // 수정 필요
    }
}
