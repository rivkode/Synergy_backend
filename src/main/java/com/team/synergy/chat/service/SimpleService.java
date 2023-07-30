package com.team.synergy.chat.service;

import com.team.synergy.chat.dto.ChatDto;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    void accept(ChatDto chatDto) {
        System.out.println("accept");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
