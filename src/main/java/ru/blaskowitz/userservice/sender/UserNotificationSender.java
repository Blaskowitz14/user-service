package ru.blaskowitz.userservice.sender;

public interface UserNotificationSender {
    void sendUserDeletedNotification(Long userId);
}
