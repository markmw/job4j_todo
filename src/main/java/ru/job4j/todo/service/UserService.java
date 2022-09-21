package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.UserStore;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserStore userStore;

    public Optional<User> add(User user) {
        return userStore.add(user);
    }

    public Collection<User> findAll() {
        return userStore.findAll();
    }

    public Optional<User> findUserByLoginAndPwd(String login, String passsword) {
        return userStore.findUserByLoginAndPwd(login, passsword);
    }
}
