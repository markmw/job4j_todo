package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {
    private final CrudRepository crudRepository;

    public List<User> findAll() {
        return crudRepository.query("from User", User.class);
    }

    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        crudRepository.run(session -> session.save(user));
        return rsl;
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        return crudRepository.optional(
                "from User where login = :fLogin and password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password));
    }
}
