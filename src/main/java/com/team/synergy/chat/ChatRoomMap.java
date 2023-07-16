package com.team.synergy.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
public class ChatRoomMap {
    private static ChatRoomMap chatRoomMap = new ChatRoomMap();
    private ConcurrentMap<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    private ChatRoomMap(){}

    public static ChatRoomMap getInstance() {
        return chatRoomMap;
    }
}
