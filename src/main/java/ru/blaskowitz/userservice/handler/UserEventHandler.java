package ru.blaskowitz.userservice.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.blaskowitz.userservice.event.UserDeletedEvent;
import ru.blaskowitz.userservice.sender.UserNotificationSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventHandler {

    private final UserNotificationSender userNotificationSender;

    @Async
    @EventListener
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        log.info("received user deleted event: {}", event);
        userNotificationSender.sendUserDeletedNotification(event.getUserId());
    }
}
