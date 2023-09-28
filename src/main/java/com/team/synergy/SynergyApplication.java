package com.team.synergy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing
@SpringBootApplication
@RestController
public class SynergyApplication {

    @GetMapping("/hello")
    public String hello() {
        return "now 2023 09 29";
    }

    public static void main(String[] args) {
        SpringApplication.run(SynergyApplication.class, args);
    }

}
