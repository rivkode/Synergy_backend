//package com.team.synergy.chat.dto;
//
//import com.team.synergy.chat.domain.ChatMessage;
//import lombok.Getter;
//
//import java.time.format.DateTimeFormatter;
//
//@Getter
//public class ChatMessageResponseDto {
//
//    /**
//     * 채팅 메세지 불러오기 등의 요청으로 사용하기 위한 DTO
//     * 이 클래스의 멤버(id, sender, message, createdDate, updatedDate 등의 정보를 담아 응답함
//     */
//    private Long id;
//    private String sender;
//    private String message;
//    private String createdDate;
//    private String updatedDate;
//
//    public ChatMessageResponseDto(ChatMessage entity) {
//        this.id = entity.getId();
//        this.sender = entity.getSender();
//        this.message = entity.getMessage();
//        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
//        this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
//    }
//}
