package com.team.synergy.notification;

import com.team.synergy.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final MemberService memberService;


    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> subscribe(HttpServletRequest servletRequest, HttpServletResponse response, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        System.out.println("sub가 요청되었습니다");

        return ResponseEntity.ok()
                .body(notificationService.subscribe(memberId, lastEventId));
    }
}
