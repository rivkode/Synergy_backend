package com.team.synergy.member;

import com.github.javafaker.Faker;
import com.team.synergy.login.LoginService;
import com.team.synergy.member.dto.MemberSignUpRequest;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("멤버 생성")
    @Test
    void makeMember() {
        //given
        Faker faker = new Faker(Locale.US);
        List<Member> memberList = new ArrayList<>();

        int before = memberRepository.countMembers();
        for (int i=0; i<100; i++) {
            String name = faker.name().fullName();
            String password = faker.crypto().sha256();
            String email = faker.internet().emailAddress();
            MemberSignUpRequest request = new MemberSignUpRequest(name, password, email);

            //when
            memberService.signUp(request);
        }

        int after = memberRepository.countMembers();

        //then
        Assert.assertEquals("멤버 100명 생성", 100, after - before);
    }

    @DisplayName("10만명 멤버 데이터 생성")
    @Test
    void makeMemberBigData() {
        //given
        List<Member> memberList = new ArrayList<>();
        Faker faker = new Faker(Locale.US);

        for(int i=0; i<100000; i++) {
//            Member member = Member.builder()
//                    .email(faker.internet().emailAddress())
//                    .password(faker.crypto().sha256())
//                    .name(faker.name().fullName())
//                    .createDate(LocalDateTime.now())
//                    .build();
//            memberList.add(member);
        }
        System.out.println("memberList size = " + memberList.size());

        //when
        int batchSize = 1000;
        int listSize = memberList.size();

        for (int i=0; i<listSize; i += batchSize) {
            int endIndex = i + batchSize; // 최소 index를 구한다 save를 할때 batch 단위만큼 sub 리스트로 저장하기 위해서
            List<Member> batchList = memberList.subList(i, endIndex);

            memberRepository.saveAll(batchList);
            memberRepository.flush();
        }

        //then
        Assert.assertEquals("10만건 테스트 데이터 생성", 100000, memberList.size());
    }



}
