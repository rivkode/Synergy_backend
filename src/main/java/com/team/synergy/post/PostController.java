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
        this.postService.postCreate(postDto.getTitle(), postDto.getContent());
        return ResponseEntity.ok().body("게시글 작성 성공");
    }

    @GetMapping("/postAll")
    public Result getPostList() {
        return new Result(postService.findAll());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> postDelete(@PathVariable("id") Long id) {
        Post post = this.postService.getPost(id);

        this.postService.postDelete(post);
        return ResponseEntity.ok().body("게시글 삭제 성공");
    }
}
