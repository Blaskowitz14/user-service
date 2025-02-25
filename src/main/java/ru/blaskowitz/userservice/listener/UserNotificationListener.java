package ru.blaskowitz.userservice.listener;

public interface UserNotificationListener {
    void onUserDeletedNotification(Long userId);
}
