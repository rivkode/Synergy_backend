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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public CreatePostLikeResponse createPostLike(Member member, Post post, CreatePostLikeRequest request) {
        // 이전에 member가 동일한 post에 대해 post_like를 가지고 있는지 check
        Optional<PostLike> postLikeOptional =  postLikeRepository.findPostLikeByMemberAndPost(member, post);
        if (postLikeOptional.isPresent()) {
            throw new AppException(ErrorCode.INVALID_DATA, "이미 postLike 가 존재합니다");
        } else {
            PostLike postLike = request.toEntity(member, post);
            PostLike savedPostLike = postLikeRepository.save(postLike);

            return CreatePostLikeResponse.from(savedPostLike);
        }
    }

    @Transactional
    public void deletePostLike(Member member, Post post) {
        PostLike postLike = postLikeRepository.findPostLikeByMemberAndPost(member, post)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "유효하지 않은 PostLike입니다"));
        post.deletePostLike(postLike);
        postLike.deletePostLike();
        postLikeRepository.delete(postLike);
    }
}
