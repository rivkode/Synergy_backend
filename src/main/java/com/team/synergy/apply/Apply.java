package com.team.synergy.apply;

import com.team.synergy.exception.AppException;
import com.team.synergy.member.Member;
import com.team.synergy.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter @Setter
public class Apply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "apply_time")
    private LocalDateTime applyTime; // 신청 시간

    @Column(name = "apply_status")
    @Enumerated(EnumType.STRING)
    private ApplyStatus status; // 신청 상태 [APPLY, PROCESS, DONE]

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;


    // 연관관계 메서드 //

    /**
     * 연관관계 주인을 떠나서 속성 객체에 양쪽의 값들을 넣어줘야 하기 떄문에 메서드 작성
     * 물론 비즈니스 로직에 양쪽 객체를 통해 세팅할 수 있지만 실수를 방지하기 위해
     */
    void setMember(Member member) {
        this.member = member;
        member.getApplyList().add(this);
    }

    void setProject(Project project) {
        this.project = project;
        project.setApply(this);
    }

    //==생성 메서드==//
    public static Apply createApply(Member member, Project project) {
        Apply apply = new Apply();
        apply.setMember(member);
        apply.setProject(project);
        apply.setApplyTime(LocalDateTime.now());
        apply.setStatus(ApplyStatus.APPLY);
        return apply;
    }

    //==비즈니스 로직==//
    public void cancel() {
        if (getStatus() == ApplyStatus.DONE) {
            throw new IllegalStateException("이미 신청이 완료되었습니다");
        }

        this.setStatus(ApplyStatus.CANCEL);
    }

//    public void cancelApply(Member member, Project project, Apply apply) {
//        apply.getProject().set
//
//    }

}