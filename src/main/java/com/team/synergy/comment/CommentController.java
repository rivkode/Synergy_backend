package com.team.synergy.comment;

import com.team.synergy.comment.dto.request.CreateCommentRequest;
import com.team.synergy.comment.dto.request.DeleteCommentRequest;
import com.team.synergy.comment.dto.response.CommentsResponse;
import com.team.synergy.comment.dto.response.CreateCommentResponse;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.Post;
import com.team.synergy.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(HttpServletRequest servletRequest, @RequestBody CreateCommentRequest request) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        Post post = postService.findPostById(request.getPostId());

        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(commentService.createComment(request.getComment(), member, post));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(HttpServletRequest servletRequest, @PathVariable("commentId") Long commentId) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommentsResponse> getCommentByPostId(HttpServletRequest servletRequest, @PathVariable("postId") Long postId) {

        return ResponseEntity.ok()
                .body(commentService.findCommentsByPostId(postId));
    }

}
