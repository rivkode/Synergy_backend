package com.team.synergy;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class SynergyApplicationTests {


    @Test
    void contextLoads() {
        Faker faker = new Faker(new Locale("ko"));
        String fakerName = String.valueOf(faker.name().title());
        System.out.println("fakerName = " + fakerName);
    }

}
