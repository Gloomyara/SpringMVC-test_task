package ru.antonovmikhail.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.user.model.User;
import ru.antonovmikhail.user.model.UserDtoIn;
import ru.antonovmikhail.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public User create(UserDtoIn dtoIn) {
        User user = User.builder()
                .name(dtoIn.getName())
                .email(dtoIn.getEmail())
                .build();
        return repository.save(user);
    }

    @Override
    public User update(UserDtoIn dtoIn) {
        User user = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
        user.setName(dtoIn.getName());
        user.setEmail(dtoIn.getEmail());
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User delete(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return user;
    }
}
