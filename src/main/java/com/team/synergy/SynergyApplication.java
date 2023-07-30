package com.team.synergy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SynergyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SynergyApplication.class, args);
    }

}
