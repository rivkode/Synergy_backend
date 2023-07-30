package com.team.synergy.chat;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class ChatMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            // 메시지의 바디를 읽어옵니다. (예: UTF-8로 인코딩된 텍스트 메시지를 가정)
            String messageBody = new String(message.getBody(), "UTF-8");
            System.out.println("Received message: " + messageBody);

            // 수신한 메시지를 기반으로 특정 작업을 수행합니다.
            // 예를 들어, 메시지를 데이터베이스에 저장하거나, 처리하는 로직을 추가할 수 있습니다.
            // ...

        } catch (Exception e) {
            // 메시지 처리 중 예외가 발생할 경우, 적절히 처리해줍니다.
            System.err.println("Error while processing the message: " + e.getMessage());
            // 예외를 기록하거나, 재시도 정책을 적용할 수 있습니다.
        }
    }
}
