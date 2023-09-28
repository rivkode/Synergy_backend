package com.team.synergy.chat;

import com.team.synergy.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto messageDto) {
        messageDto.setMessage(messageDto.getWriter() + "님이 채팅방에 입장하였습니다");
        template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto messageDto) {
        template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }
}
