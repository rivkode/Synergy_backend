package com.team.synergy.post;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public String postCreate(PostDto postDto) {
        Post post = Post.postCreate(postDto.getTitle(), postDto.getContent());
        postRepository.save(post);
        return "게시글 작성 성공";
    }

    public List<PostDto> findAll() {
        return PostDto.from(postRepository.findAll());
    }

    public Post getPost(Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "게시글이 없습니다");
        }
    }

    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId).get();
        this.postRepository.delete(post);
    }
}
