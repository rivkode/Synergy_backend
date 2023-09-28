package com.team.synergy.post;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.follow.FollowService;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.dto.PostGetResponse;
import com.team.synergy.post.dto.request.CreatePostRequest;
import com.team.synergy.post.dto.response.CreatePostResponse;
import com.team.synergy.post.dto.response.InfoPostResponse;
import com.team.synergy.post.dto.response.ListPostResponse;
import com.team.synergy.post.dto.response.PostIdsGetResponse;
import com.team.synergy.postlike.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final FollowService followService;
    private final PostLikeService postLikeService;

    @Transactional
    public CreatePostResponse createPost(Member member, CreatePostRequest request) {
        Post savedPost = postRepository.save(request.toEntity(member));
        System.out.println(savedPost.getMember().getName());
        return CreatePostResponse.from(savedPost);
    }

    public Post findPostById(Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "find 할 게시글이 없습니다");
        }
    }

    public void deletePost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Member member = memberService.findMemberById(postOptional.get().getMember().getId());


        }
    }

    public Page<PostGetResponse> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostGetResponse> postGetResponses = PostGetResponse.toResponses(posts);
        return postGetResponses;
    }

    public ListPostResponse getPostsWithNoOffset(Long postId) {
        List<Post> posts = postRepository.findAllWithId(postId);
        List<InfoPostResponse> postResponses = new ArrayList<>();

        for (Post p : posts) {
            postResponses.add(InfoPostResponse.from(p));
        }
        ListPostResponse listPostResponse = ListPostResponse.from(postResponses);

        return listPostResponse;
    }

    public Page<PostGetResponse> getPostsByMember(Pageable pageable, String memberId) {
        Page<Post> posts = postRepository.findByMemberId(pageable, memberId);
        Page<PostGetResponse> postGetResponses = PostGetResponse.toResponses(posts);

        return postGetResponses;
    }

    public Page<PostGetResponse> searchPosts(Pageable pageable, String keyword) {
        Page<Post> posts = postRepository.findByTitleContaining(keyword, pageable);
        Page<PostGetResponse> postGetResponses = PostGetResponse.toResponses(posts);

        return postGetResponses;
    }

    public InfoPostResponse postInfo(Long postId) {
        Post post = findPostById(postId);
        return InfoPostResponse.from(post);
    }

    public Page<PostGetResponse> getPostsByFollowings(Pageable pageable, String memberId) {
        List<String> followingIds = followService.findFollowingIdsByMemberId(memberId);
        List<PostGetResponse> allPosts = new ArrayList<>();

        for (String id : followingIds) {
            // Slice를 사용하여 페이지별로 게시물을 로드
            Slice<PostGetResponse> postsByMember = getPostsByMember(pageable, id);

            // Slice의 내용을 List에 추가
            allPosts.addAll(postsByMember.getContent());

            // 다음 페이지가 있는지 확인하고 필요하다면 루프 계속
            while (postsByMember.hasNext()) {
                pageable = pageable.next(); // 다음 페이지로 이동
                postsByMember = getPostsByMember(pageable, id);
                allPosts.addAll(postsByMember.getContent());
            }
        }

        // allPosts에는 모든 팔로잉 사용자의 게시물이 포함됩니다.

        // Pageable 관련 로직 추가

        return new PageImpl<>(allPosts, pageable, allPosts.size());

    }

    public PostIdsGetResponse getPostLikeIdsByMemberId(String memberId) {
        List<Long> postIds = postLikeService.getPostIdsByMemberId(memberId);

        return PostIdsGetResponse.from(postIds);
    }
}
