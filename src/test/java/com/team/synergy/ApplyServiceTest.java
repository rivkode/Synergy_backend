package com.team.synergy;

import com.team.synergy.apply.Apply;
import com.team.synergy.apply.ApplyRepository;
import com.team.synergy.apply.ApplyService;
import com.team.synergy.apply.ApplyStatus;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.Project;
import com.team.synergy.project.ProjectRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApplyServiceTest {

    @Autowired
    ApplyService applyService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ApplyRepository applyRepository;

    @Test
    public void 프로젝트신청() throws Exception {
        //given
        LocalDateTime currentDate = LocalDateTime.now();
        List<Apply> applyList = new ArrayList<>();

        Member member = Member.builder()
                .name("이종훈")
                .email("rivs@kakao.com")
                .password("1234")
                .createDate(currentDate)
                .applyList(applyList)
                .build();

        memberRepository.save(member);


        Project project = Project.builder()
                .name("프로젝트 이름")
                .content("프로젝트 내용")
                .field("프로젝트 분야")
                .createDate(currentDate)
                .endDate(currentDate)
                .build();

        projectRepository.save(project);



        Apply apply = Apply.createApply(member, project);

        //when
        Long applyId = applyService.apply(member.getId(), project.getId());

        //then
        Apply getApply = applyRepository.findById(applyId).get();

        ApplyStatus status = ApplyStatus.APPLY;
        ApplyStatus applyStatus = getApply.getStatus();
        System.out.println("status = " + status);
        System.out.println("applyStatus = " + applyStatus);
        Assert.assertEquals("신청시 상태는 APPLY", ApplyStatus.APPLY, getApply.getStatus());
        Assert.assertEquals("멤버의 프로젝트와 프로젝트의 멤버는 신청한 객체내용과 같다", "이종훈", apply.getMember().getName());
        Assert.assertEquals("멤버의 프로젝트와 프로젝트의 멤버는 신청한 객체내용과 같다", "프로젝트 이름", apply.getProject().getName());
    }
}
