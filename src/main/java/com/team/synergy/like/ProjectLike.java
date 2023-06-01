package com.team.synergy.like;

import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Getter @Setter
public class ProjectLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "like_time")
    private LocalDateTime likeTime; // 좋아요 시간

    @Column(name = "like_status")
    @Enumerated(EnumType.STRING)
    private LikeStatus status; // 좋아요 상태 [LIKE, UNLIKE]



    public static ProjectLike createProjectLike(Member member, Project project) {
        ProjectLike projectLike = new ProjectLike();
        projectLike.setMember(member);
        projectLike.setProject(project);
        projectLike.setLikeTime(LocalDateTime.now());
        projectLike.setStatus(LikeStatus.LIKE);
        return projectLike;
    }

    public void cancelProjectLike() {
        if (getStatus() == LikeStatus.UNLIKE) {
            throw new IllegalStateException("이미 좋아요 취소 상태입니다");
        } else {
            System.out.println("CANCEL");
            this.setStatus(LikeStatus.UNLIKE);
        }
    }
}
