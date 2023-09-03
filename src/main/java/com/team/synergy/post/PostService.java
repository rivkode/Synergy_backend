package com.team.synergy.post;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.post.dto.PostDto;
import com.team.synergy.post.dto.PostGetResponse;
import com.team.synergy.post.dto.request.CreatePostRequest;
import com.team.synergy.post.dto.response.CreatePostResponse;
import com.team.synergy.post.dto.response.InfoPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public CreatePostResponse createPost(Member member, CreatePostRequest request) {
        Post savedPost = postRepository.save(request.toEntity(member));
        System.out.println(savedPost.getMember().getName());
        return CreatePostResponse.from(savedPost);
    }

    public Post findPostById(Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "find 할 게시글이 없습니다");
        }
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Page<PostGetResponse> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        System.out.println("post 개수" + posts.getTotalElements());
        Page<PostGetResponse> postGetResponses = PostGetResponse.toResponses(posts);
        return postGetResponses;
    }

    public Page<PostGetResponse> searchPosts(Pageable pageable, String keyword) {
        Page<Post> posts = postRepository.findByTitleContaining(keyword, pageable);
        Page<PostGetResponse> postGetResponses = PostGetResponse.toResponses(posts);

        return postGetResponses;
    }

    public InfoPostResponse postInfo(Long postId) {
        Post post = findPostById(postId);
        return InfoPostResponse.from(post);
    }
}
