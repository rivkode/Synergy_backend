package com.team.synergy.notification.dto.response;

import com.team.synergy.member.Member;
import com.team.synergy.notification.Notification;
import com.team.synergy.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NotificationResponse {
    private Long id;
//    private Member receiver;
    private String content;
    private NotificationType type;

    @Builder
    public NotificationResponse(Long id, String content, NotificationType type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    public static NotificationResponse from(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .type(notification.getNotificationType())
                .build();
    }
}
