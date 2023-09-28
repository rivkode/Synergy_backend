package com.team.synergy.chat;

import com.team.synergy.chat.dto.ChatRoomDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoomDto> chatRoomDtoMap;

    @PostConstruct
    private void init() {
        chatRoomDtoMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRooms() {
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRoomDtoMap.get(roomId);
    }

    public ChatRoomDto createChatRoomDto(String name) {
        ChatRoomDto roomDto = ChatRoomDto.create(name);
        chatRoomDtoMap.put(roomDto.getRoomId(), roomDto);

        return roomDto;
    }
}
