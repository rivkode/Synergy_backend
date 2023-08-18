package com.team.synergy.post;

import com.team.synergy.generic.Result;
import com.team.synergy.post.dto.PostGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<String> postCreate(@RequestBody PostDto postDto) {
        return ResponseEntity.ok().body(postService.createPost(postDto));
    }

    @GetMapping("/{id}")
    public Result getPost(@PathVariable("id") Long postId) {
        return new Result(postService.findPostById(postId));
    }


    @GetMapping("/recent")
    public ResponseEntity<Page<PostGetResponse>> getPostList(@PageableDefault(size = 13, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostGetResponse> postGetResponses = postService.getPosts(pageable);

        return ResponseEntity.ok()
                .body(postGetResponses);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> postDelete(@PathVariable("id") Long postId) {
        this.postService.postDelete(postId);
        return ResponseEntity.ok().body("게시글 삭제 성공");
    }
}
