package com.team.synergy.apply;

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
@DynamicUpdate //변경한 필드만 대응
@Entity
@Getter
public class Apply extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "apply_status")
    @Enumerated(EnumType.STRING)
    private ApplyStatus status; // 신청 상태 [APPLY, CANCEL, PROCESS, DONE]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setStatus(ApplyStatus status) {
        this.status = status;
    }

    /**
     * 연관관계 주인을 떠나서 속성 객체에 양쪽의 값들을 넣어줘야 하기 떄문에 메서드 작성
     * 물론 비즈니스 로직에 양쪽 객체를 통해 세팅할 수 있지만 실수를 방지하기 위해
     */

    public Apply(Member member, Project project) {
        this.member = member;
        this.project = project;
        this.status = ApplyStatus.PROCESS;
    }
}