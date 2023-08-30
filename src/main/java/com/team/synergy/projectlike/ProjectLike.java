package com.team.synergy.projectlike;

import com.team.synergy.BaseTime;
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
public class ProjectLike extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "project_like_status")
    @Enumerated(EnumType.STRING)
    private ProjectLikeStatus status; // 좋아요 상태 [LIKE, UNLIKE]

    public ProjectLike(Member member, Project project) {
        this.member = member;
        this.project = project;
        project.getLikes().add(this);
        this.status = ProjectLikeStatus.LIKE;
    }

    public void cancelProjectLike() {
        if (getStatus() == ProjectLikeStatus.UNLIKE) {
            throw new IllegalStateException("이미 좋아요 취소 상태입니다");
        } else {
            System.out.println("CANCEL");
            this.setStatus(ProjectLikeStatus.UNLIKE);
        }
    }
}
