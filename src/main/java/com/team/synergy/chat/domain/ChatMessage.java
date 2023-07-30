//package com.team.synergy.chat.domain;
//
//import com.team.synergy.BaseTime;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//
//@NoArgsConstructor
//@Getter
//@Entity
//public class ChatMessage extends BaseTime {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String sender;
//
//    private String message;
//
//    @ManyToOne
//    private ChatRoom chatRoom;
//
//    @Builder
//    public ChatMessage(String sender, String message, ChatRoom chatRoom) {
//        this.sender = sender;
//        this.message = message;
//        this.chatRoom = chatRoom;
//    }
//}
