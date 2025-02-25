package ru.blaskowitz.userservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserNotificationListenerImpl implements UserNotificationListener {

    private final CacheManager cacheManager;

    @Override
    @KafkaListener(topics = "${spring.kafka.topics.user-deleted-topic-name}",
            containerFactory = "kafkaListenerContainerFactory")
    public void onUserDeletedNotification(Long userId) {
        log.info("received user deleted notification: {}", userId);
        Cache usersCache = cacheManager.getCache("users");
        usersCache.evict(userId);
        log.info("user evicted from cache {}", usersCache.getName());
    }
}
