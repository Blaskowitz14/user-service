package ru.blaskowitz.userservice.service;

import ru.blaskowitz.userservice.model.User;

public interface UserService {
    User createUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);
}
