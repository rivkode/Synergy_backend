//package com.team.synergy.chat.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.*;
//
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry brokerRegistry) {
//
//        // 메세지를 구독하는 요청 url - 즉 메세지를 받을 때
//        brokerRegistry.enableSimpleBroker("/sub");
//
//        // 메세지를 발행하는 요청 url - 즉 메세지를 보낼 때
//        brokerRegistry.setApplicationDestinationPrefixes("/pub");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry endpointRegistry) {
//        // stomp 접속 주소 url -> ws-stomp
//        endpointRegistry.addEndpoint("/ws-stomp") // 연결될 엔드포인트 // .setAllowedOriginPatterns("*")
//                .withSockJS(); // socketJs를 연결한다는 설정
//    }
//
//
//}
//
