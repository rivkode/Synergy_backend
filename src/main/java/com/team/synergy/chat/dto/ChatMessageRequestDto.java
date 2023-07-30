//package com.team.synergy.chat.dto;
//
//import com.team.synergy.chat.domain.ChatMessage;
//import com.team.synergy.chat.domain.ChatRoom;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//public class ChatMessageRequestDto {
//
//    /**
//     * 채팅 메세지(ChatMessage) 엔티티 생성/수정 등의 요청으로 사용하기 위한 DTO
//     * 각 메세지마다 발송자와 메세지 연결된 채팅방 정보가 담겨 요청하게 됨
//     */
//
//    private String sender;
//    private String message;
//    private ChatRoom chatRoom;
//
//    @Builder
//    public ChatMessageRequestDto(String sender, String message, ChatRoom chatRoom) {
//        this.sender = sender;
//        this.message = message;
//        this.chatRoom = chatRoom;
//    }
//
//    public ChatMessage toEntity() {
//        return ChatMessage.builder()
//                .sender(this.sender)
//                .message(this.message)
//                .chatRoom(this.chatRoom)
//                .build();
//    }
//}
