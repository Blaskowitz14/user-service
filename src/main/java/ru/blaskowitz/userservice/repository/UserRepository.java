package ru.blaskowitz.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blaskowitz.userservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndIsDeletedFalse(Long id);

    boolean existsByUsername(String username);
}
