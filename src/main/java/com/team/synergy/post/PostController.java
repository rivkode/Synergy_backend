package com.team.synergy.post;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.dto.PostGetResponse;
import com.team.synergy.post.dto.request.CreatePostRequest;
import com.team.synergy.post.dto.response.CreatePostResponse;
import com.team.synergy.post.dto.response.InfoPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId) {
        Post post = postService.findPostById(postId);
        postService.deletePost(post);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PostGetResponse>> searchPostList(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        Page<PostGetResponse> postGetResponses = postService.searchPosts(pageable, keyword);

        return ResponseEntity.ok()
                .body(postGetResponses);
    }
}
