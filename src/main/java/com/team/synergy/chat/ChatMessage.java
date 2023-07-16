package com.team.synergy.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChatMessage {
    /**
     * ENTER, 입장
     * TALK는 말 그대로 내용이 해당 채팅방을 SUB구독하고 있는 모든 클라이언트들에게 전달된다
     */
    public enum MessageType {
        JOIN, ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String senderId;
    private String userId;
    private String message;
}
