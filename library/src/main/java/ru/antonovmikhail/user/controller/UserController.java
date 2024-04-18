package ru.antonovmikhail.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.user.model.User;
import ru.antonovmikhail.user.model.UserDtoIn;
import ru.antonovmikhail.user.service.UserService;
import ru.antonovmikhail.util.Views;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class UserController {

    private final UserService userService;

    @JsonView(Views.UserSummary.class)
    @GetMapping("users")
    public ResponseEntity<List<User>> getAll() {
        log.info("Received GET api/v1/users request.");
        return ResponseEntity.ok(userService.findAll());
    }

    @JsonView(Views.UserDetails.class)
    @GetMapping("user/{id}")
    public ResponseEntity<User> getById(@org.hibernate.validator.constraints.UUID UUID id) {
        log.info("Received GET api/v1/user/{} request.", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @JsonView(Views.UserDetails.class)
    @PostMapping("user")
    public ResponseEntity<User> post(@Valid @RequestBody UserDtoIn dtoIn) {
        log.info("Received POST api/v1/user request, userDtoIn = {}", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(dtoIn));
    }

    @JsonView(Views.UserDetails.class)
    @PutMapping("user")
    public ResponseEntity<User> put(@Valid @RequestBody UserDtoIn dtoIn) {
        log.info("Received PUT api/v1/user request, userDtoIn = {}", dtoIn);
        return ResponseEntity.ok(userService.update(dtoIn));
    }

    @JsonView(Views.UserDetails.class)
    @DeleteMapping("user/{id}")
    public ResponseEntity<User> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        log.info("Received DELETE api/v1/user/{} request.", id);
        return ResponseEntity.ok(userService.delete(id));
    }
}
