package com.team.synergy.project;

import com.github.javafaker.Faker;
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
public class ProjectServiceTest {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @DisplayName("프로젝트 생성")
    @Test
    void makeProject() {
        //given
        Faker faker = new Faker(Locale.US);
        LocalDateTime currentDate = LocalDateTime.now();
        List<Integer> integerList = new ArrayList<>();

        for (int i=0; i<10; i++) {
            integerList.add(i);
            /**
             * 여기서 객체 만들 필요 없음, projectCreate내에서 객체 생성함, 값만 넘겨주자
             */
            String name = faker.company().name();
            String content = faker.lorem().paragraph();
            String field = faker.job().field();
            LocalDateTime endDate = LocalDateTime.parse("2023-10-11T12:59:59");

            projectService.projectCreate(name, content, field, currentDate, endDate);
        }

        //when

        //then
        Assert.assertEquals("프로젝트 100건 생성", 10, integerList.size());
    }

    @DisplayName("10만건 프로젝트 데이터 생성")
    @Test
    void makeProjectBigData() {
        //given
        List<Project> projectList = new ArrayList<>();
        Faker faker = new Faker(Locale.US);
        LocalDateTime currentDate = LocalDateTime.now();

        for(int i=0; i<100000; i++) {
            Project project = Project.builder()
                    .name(faker.company().name())
                    .content(faker.lorem().sentence())
                    .field(faker.job().field())
                    .createDate(currentDate)
                    .endDate(LocalDateTime.parse("2023-10-11T12:59:59"))
                    .build()
                    ;

            projectList.add(project);
        }
        System.out.println("projectList size = " + projectList.size());

        //when
        int batchSize = 1000; // 배치 사이즈
        int listSize = projectList.size(); // 전체 데이터 크기

        for (int i=0; i<listSize; i += batchSize) {
            int endIndex = i + batchSize; // 최소 index를 구한다 save를 할때 batch 단위만큼 subList로 저장하기 위해서
            List<Project> batchList = projectList.subList(i, endIndex);

            projectRepository.saveAll(batchList);
            projectRepository.flush();
        }

        //then
        Assert.assertEquals("10만건 테스트 데이터 생성", 100000, projectList.size());




    }

    @DisplayName("프로젝트 검색")
    @Test
    void findProject() {
        //given

        //when

        //then
    }

    @DisplayName("프로젝트 삭제")
    @Test
    void deleteProject() {
        //given

        //when

        //then
    }

}
