package com.example.test.omnivore2trendithon2025.notification.api;

import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.notification.api.dto.response.NotificationsResDto;
import com.example.test.omnivore2trendithon2025.notification.application.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(
            @CurrentUserEmail String email) {
        return ResponseEntity.ok(notificationService.connect(email));
    }

    @PostMapping("/send/{targetMemberId}")
    public ResponseEntity<String> send(@CurrentUserEmail String email,
                                       @PathVariable Long targetMemberId) {
        notificationService.send(email, targetMemberId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/notifications")
    public ResponseEntity<NotificationsResDto> getNotifications(@CurrentUserEmail String email) {
        return ResponseEntity.ok(notificationService.getNotifications(email));
    }

}
