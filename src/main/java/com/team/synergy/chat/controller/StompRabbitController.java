package com.team.synergy.chat.controller;

import com.team.synergy.chat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompRabbitController {
    private final RabbitTemplate template;
    private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
    private final static String CHAT_QUEUE_NAME = "chat.queue";

    @MessageMapping("chat.enter.{chatRoomId}")
    public void enter(ChatDto chatDto, @DestinationVariable String chatRoomId) {
        chatDto.setMessage("입장하셨습니다");
        chatDto.setRegDate(LocalDateTime.now());

        //exchange
        template.convertAndSend(CHAT_EXCHANGE_NAME, "room." + chatRoomId, chatDto);
    }

    @MessageMapping("chat.message.{chatRoomId}")
    public void send(ChatDto chatDto, @DestinationVariable String chatRoomId) {
        chatDto.setRegDate(LocalDateTime.now());

        template.convertAndSend(CHAT_EXCHANGE_NAME, "room." + chatRoomId, chatDto);
    }

    @RabbitListener(queues = CHAT_EXCHANGE_NAME)
    public void receive(ChatDto chatDto) {
        log.info("chatDto.getMessage() = {}", chatDto.getMessage());
    }
}
