package ru.blaskowitz.userservice.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserNotificationSenderImpl implements UserNotificationSender {

    @Value("${spring.kafka.user-deleted-topic-name}")
    private String userDeletedTopicName;

    private final KafkaTemplate<String, Long> kafkaTemplate;

    @Override
    public void sendUserDeletedNotification(Long userId) {
        log.info("sending user deleted notification for user {}", userId);
        kafkaTemplate.send(userDeletedTopicName, userId);
    }
}
