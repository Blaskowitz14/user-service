package ru.blaskowitz.userservice.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserDeletedEvent extends ApplicationEvent {
    private final transient Long userId;

    public UserDeletedEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
}
