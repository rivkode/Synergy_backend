package com.team.synergy.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ExChatController {
    private final SimpMessagingTemplate template;

    @PostMapping("/greetings")
    public void greet(String greeting) {
        String text = "[" + greeting + "]";
        this.template.convertAndSend("/topic/greetings", text);
    }
}
