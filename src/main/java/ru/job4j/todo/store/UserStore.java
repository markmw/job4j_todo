package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {
    private final SessionFactory sf;

    public List<User> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> collection = session.createQuery(
                "from User", User.class).list();
        session.close();
        return collection;
    }

    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        rsl = Optional.of(user);
        session.close();
        return rsl;
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        Session session = sf.openSession();
        session.beginTransaction();
        Optional<User> user = session.createQuery(
                "from User where login = :fLogin and password = :fPassword", User.class)
                .setParameter("fLogin", login)
                .setParameter("fPassword", password)
                .uniqueResultOptional();
        session.close();
        return user;
    }
}
