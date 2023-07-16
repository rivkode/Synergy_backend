package com.team.synergy.chat;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {


    //전체 채팅방 조회
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRooms = new ArrayList<>(ChatRoomMap.getInstance().getChatRooms().values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    // roomId 기준으로 채팅방 조회
    public ChatRoom findRoomById(String roomId) {
        return ChatRoomMap.getInstance().getChatRooms().get(roomId);
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
