package ru.antonovmikhail.user.service;

import ru.antonovmikhail.user.model.User;
import ru.antonovmikhail.user.model.UserDtoIn;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User findById(UUID id);

    List<User> findAll();

    User create(UserDtoIn userDtoIn);

    User update(UserDtoIn userDtoIn);

    User delete(UUID id);

}
