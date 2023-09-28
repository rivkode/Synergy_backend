package com.team.synergy.post;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.dto.PostGetResponse;
import com.team.synergy.post.dto.request.CreatePostRequest;
import com.team.synergy.post.dto.response.CreatePostResponse;
import com.team.synergy.post.dto.response.InfoPostResponse;
import com.team.synergy.post.dto.response.ListPostResponse;
import com.team.synergy.post.dto.response.PostIdsGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<CreatePostResponse> createPost(HttpServletRequest servletRequest, @RequestBody CreatePostRequest request) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(member, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoPostResponse> getPost(@PathVariable("id") Long postId) {
        return ResponseEntity.ok()
                .body(postService.postInfo(postId));
    }


    @GetMapping("/recent")
    public ResponseEntity<Page<PostGetResponse>> getPostList(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostGetResponse> postGetResponses = postService.getPosts(pageable);

        return ResponseEntity.ok()
                .body(postGetResponses);
    }

    @GetMapping("/recent/v2")
    public ResponseEntity<ListPostResponse> getPostListWithNoOffset(HttpServletRequest servletRequest, @Param("end") String end) {
        ListPostResponse listPostResponse = postService.getPostsWithNoOffset(Long.valueOf(end));

        return ResponseEntity.ok()
                .body(listPostResponse);
    }

    @GetMapping("/followings")
    public ResponseEntity<Page<PostGetResponse>> getPostListByFollowings(HttpServletRequest servletRequest, @PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Page<PostGetResponse> postGetResponses = postService.getPostsByFollowings(pageable, memberId);

        return ResponseEntity.ok()
                .body(postGetResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PostGetResponse>> searchPostList(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        Page<PostGetResponse> postGetResponses = postService.searchPosts(pageable, keyword);

        return ResponseEntity.ok()
                .body(postGetResponses);
    }

    @GetMapping
    public ResponseEntity<Page<PostGetResponse>> getPostListByMember(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable, @Param("authorId") String memberId) {
        Page<PostGetResponse> postGetResponses = postService.getPostsByMember(pageable, memberId);

        return ResponseEntity.ok()
                .body(postGetResponses);

    }

    @GetMapping("/me/likes")
    public ResponseEntity<PostIdsGetResponse> getMyPostIds(HttpServletRequest servletRequest) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        PostIdsGetResponse postIdsGetResponse = postService.getPostLikeIdsByMemberId(memberId);

        return ResponseEntity.ok()
                .body(postIdsGetResponse);
    }
}
