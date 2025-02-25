package ru.blaskowitz.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.blaskowitz.userservice.model.User;
import ru.blaskowitz.userservice.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Tag(name = "User API", description = "Операции для управления пользователями")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Создание пользователя", description = "Создает нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создан",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }

    @Operation(summary = "Получение пользователя", description = "Возвращает пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUser(id));
    }

    @Operation(summary = "Удаление пользователя", description = "Удаляет пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удален", content = @Content),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("User deleted");
    }
}
