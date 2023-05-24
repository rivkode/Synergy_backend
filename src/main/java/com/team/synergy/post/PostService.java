package com.team.synergy.post;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void postCreate(String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .build()
                ;

        postRepository.save(post);
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

    public void postDelete(Post post) {
        this.postRepository.delete(post);
    }
}
