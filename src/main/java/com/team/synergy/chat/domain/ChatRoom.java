//package com.team.synergy.chat.domain;
//
//import com.team.synergy.BaseTime;
//import com.team.synergy.chat.dto.ChatRoomRequestDto;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@NoArgsConstructor
//@Getter
//@Entity
//public class ChatRoom extends BaseTime {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String roomName;
//
//    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
//    private List<ChatMessage> chatMessageList;
//
//    @Builder
//    public ChatRoom(String roomName) {
//        this.roomName = roomName;
//    }
//
//    public Long update(ChatRoomRequestDto requestDto) {
//        this.roomName = requestDto.getRoomName();
//        return this.id;
//    }
//}
