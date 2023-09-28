package com.team.synergy.comment;

import com.team.synergy.comment.dto.response.CommentsResponse;
import com.team.synergy.comment.dto.response.CreateCommentResponse;
import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.notification.NotificationService;
import com.team.synergy.notification.NotificationType;
import com.team.synergy.post.Post;
import com.team.synergy.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final NotificationService notificationService;

    @Transactional
    public CreateCommentResponse createComment(String content, Member member, Post post) {
        Comment comment = new Comment(content, member, post);
        Comment savedComment = commentRepository.save(comment);

        comment.addPost(post);
        comment.addMember(member);

        Member receiver = post.getMember();
        System.out.println("send 하기 전");

        // 댓글에 대한 알림 서비스
        notificationService.send(receiver, NotificationType.COMMENT, String.valueOf(post.getId()));

        System.out.println("send 한 후");


        return new CreateCommentResponse(savedComment.getId());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, "comment가 존재하지 않습니다"));

        comment.deletePost();
        comment.deleteMember();
        commentRepository.delete(comment);
    }

    public CommentsResponse findCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return CommentsResponse.from(comments);
    }
}
