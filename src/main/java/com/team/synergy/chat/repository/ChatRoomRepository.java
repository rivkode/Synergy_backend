//package com.team.synergy.chat.repository;
//
//import com.team.synergy.chat.domain.ChatRoom;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
//
//    // ChatRoom 조회 - RoomName 검색
//    ChatRoom findByRoomName(String roomName);
//
//    //ChatRoom 목록조회 - 기본정렬순, roomName 검색, 정확히 일치
//    List<ChatRoom> findAllByRoomName(String roomName);
//
//    // ChatRoom 목록조회 - 조건정렬순, roomName 검색, 정확히 일치
//    List<ChatRoom> findAllByRoomName(String roomName, Sort sort);
//
//    // ChatRoom 목록조회 - 기본정렬순, RoomName 검색, 포함일치
//    List<ChatRoom> findAllByRoomNameContaining(String roomName);
//
//    // ChatRoom 목록조회 - 조건정렬순, RoomName 검색 포함일치
//    List<ChatRoom> findAllByRoomNameContaining(String roomName, Sort sort);
//}
