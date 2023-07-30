//package com.team.synergy.chat.service;
//
//import com.team.synergy.chat.domain.ChatMessage;
//import com.team.synergy.chat.domain.ChatRoom;
//import com.team.synergy.chat.dto.ChatMessageRequestDto;
//import com.team.synergy.chat.dto.ChatMessageResponseDto;
//import com.team.synergy.chat.repository.ChatMessageRepository;
//import com.team.synergy.chat.repository.ChatRoomRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class ChatMessageService {
//    private final ChatRoomRepository chatRoomRepository;
//    private final ChatMessageRepository chatMessageRepository;
//
//    // ChatMessage 조회
//    @Transactional
//    public ChatMessageResponseDto findById(final Long chatmessageId) {
//        ChatMessage chatMessageEntity = this.chatMessageRepository.findById(chatmessageId).orElseThrow(
//                () -> new IllegalArgumentException("해당 ChatMessage가 존재하지 않습니다. chatMessageId = " + chatmessageId)
//        );
//        return new ChatMessageResponseDto(chatMessageEntity);
//    }
//
//    // ChatMessage 생성
//    @Transactional
//    public Long save(final Long chatRoomId, final ChatMessageRequestDto requestDto) {
//        ChatRoom chatRoomEntity = this.chatRoomRepository.findById(chatRoomId).orElseThrow(
//                () -> new IllegalArgumentException("해당 ChatRoom이 존재하지 않습니다. chatRoomId = " + chatRoomId)
//        );
//        return this.chatMessageRepository.save(requestDto.toEntity()).getId();
//    }
//
//    // ChatMessage 삭제
//    @Transactional
//    public void delete(final Long chatMessageId) {
//        ChatMessage chatMessageEntity = this.chatMessageRepository.findById(chatMessageId).orElseThrow(
//                () -> new IllegalArgumentException("해당 ChatMessage가 존재하지 않습니다. chatMessageId = " + chatMessageId)
//        );
//        this.chatMessageRepository.delete(chatMessageEntity);
//    }
//
//    ////////////////////////////////////////////
//    @Transactional
//    public List<ChatMessageResponseDto> findAllByChatRoomIdAsc(final Long chatRoomId) {
//        ChatRoom chatRoomEntity = this.chatRoomRepository.findById(chatRoomId).orElseThrow(
//                () -> new IllegalArgumentException("해당 ChatRoom이 존재하지 않습니다. chatRoomId = " + chatRoomId));
//        Sort sort = Sort.by(Sort.Direction.ASC, "id");
//        List<ChatMessage> chatMessageList = this.chatMessageRepository.findAllByChatRoom(chatRoomEntity, sort);
//        return chatMessageList.stream().map(ChatMessageResponseDto::new).collect(Collectors.toList());
//    }
//
//    /** 특정 채팅방 ChatMessage 목록조회 - 최신순, List, ChatRoomId 검색 */
//    @Transactional
//    public List<ChatMessageResponseDto> findAllByChatRoomIdDesc(final Long chatRoomId) {
//        ChatRoom chatRoomEntity = this.chatRoomRepository.findById(chatRoomId).orElseThrow(
//                () -> new IllegalArgumentException("해당 ChatRoom이 존재하지 않습니다. chatRoomId = " + chatRoomId));
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        List<ChatMessage> chatMessageList = this.chatMessageRepository.findAllByChatRoom(chatRoomEntity, sort);
//        return chatMessageList.stream().map(ChatMessageResponseDto::new).collect(Collectors.toList());
//    }
//}
