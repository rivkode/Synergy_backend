package com.team.synergy.post;

import com.team.synergy.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> postCreate(@RequestBody PostDto postDto) {
        return ResponseEntity.ok().body(postService.postCreate(postDto));
    }

    @GetMapping("/detail/{id}")
    public Result getPost(@PathVariable("id") Long postId) {
        return new Result(postService.getPost(postId));
    }


    @GetMapping("/list")
    public Result getPostList() {
        return new Result(postService.findAll());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> postDelete(@PathVariable("id") Long postId) {
        this.postService.postDelete(postId);
        return ResponseEntity.ok().body("게시글 삭제 성공");
    }
}
