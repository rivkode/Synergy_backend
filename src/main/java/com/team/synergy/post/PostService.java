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
    private final PostReository postReository;

    public void postCreate(String subject, String content) {
        Post post = Post.builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .build()
                ;

        postReository.save(post);
    }

    public List<PostDto> findAll() {
        return PostDto.from(postReository.findAll());
    }

    public Post getPost(Long id) {
        Optional<Post> post = this.postReository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "데이터가 없습니다");
        }
    }

    public void postDelete(Post post) {
        this.postReository.delete(post);
    }
}
