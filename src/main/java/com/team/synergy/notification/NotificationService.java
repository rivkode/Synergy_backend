package com.team.synergy.notification;

import com.team.synergy.apply.Apply;
import com.team.synergy.member.Member;
import com.team.synergy.notification.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Long DEFAULT_TIMEOUT = 1000 * 30L;
    private final EmitterRepository emitterRepository;

    public SseEmitter subscribe(String memberId, String lastEventId) {
        String id = memberId + "_" + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

//        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onCompletion(() -> emitterRepository.deleteAllEmitterStartWithId(id));
//        emitter.onTimeout(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteAllEmitterStartWithId(id));
        emitter.onError((e) -> emitterRepository.deleteAllEmitterStartWithId(id));

        sendToClient(emitter, id, "EventStream Created. [memberId = " + memberId + "]");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithByMemberId(memberId);
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
            System.out.println("isEmpty");
        }
        return emitter;
    }

    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
            System.out.println("id : " + id + " ||      data : " + data);
        } catch (IOException exception) {
            emitterRepository.deleteById(id);
            throw new RuntimeException("연결 오류");
        }
    }

    public void send(Member receiver, NotificationType type, String content) {
        Notification notification = createNotification(receiver, type, content);
        String memberId = receiver.getId();

        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithByMemberId(memberId);
        System.out.println(sseEmitters.size());
        System.out.println("see Emitter 객체" + sseEmitters.toString());
        sseEmitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notification);
                    System.out.println("saveCache 저장/ 데이터 캐시 저장(유실된 데이터 처리하기 위함)");
                    sendToClient(emitter, key, NotificationResponse.from(notification));
                    System.out.println("데이터 전송");
                }
        );
    }

    private Notification createNotification(Member receiver, NotificationType type, String content) {
        return Notification.builder()
                .receiver(receiver)
                .content(content)
                .notificationType(type)
                .isRead(false)
                .build();
    }
}
