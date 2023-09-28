package com.team.synergy.postlike;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.post.Post;
import com.team.synergy.post.PostService;
import com.team.synergy.postlike.dto.request.PostLikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;
    private final MemberService memberService;
    private final PostService postService;

    @PutMapping("/{postId}/like")
    public ResponseEntity<Void> updatePostLike(HttpServletRequest servletRequest, @PathVariable Long postId, @RequestBody PostLikeType type) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);
        Post post = postService.findPostById(postId);
        postLikeService.updatePostLike(member, post, type);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
