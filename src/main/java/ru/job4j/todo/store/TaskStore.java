package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final SessionFactory sf;

    public int executeDone(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        int res = session.createQuery("update Task set done = :fDone where id = :fId")
                .setParameter("fDone", true)
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return res;
    }

    public int delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        int res = session.createQuery(
                "delete from Task where id = :fId")
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return res;
    }

    public int update(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        int res = session.createQuery(
                "update Task set description = :fDescription, done = :fDone where id = :fId")
                .setParameter("fDescription", task.getDescription())
                .setParameter("fDone", task.isDone())
                .setParameter("fId", task.getId())
                .executeUpdate();
        session.close();
        return res;
    }

    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Optional<Task> task = session.createQuery(
                "from Task where id = :fId", Task.class)
                .setParameter("fId", id).uniqueResultOptional();
        session.close();
        return task;
    }

    public Task add(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.close();
        return task;
    }

    public List<Task> getAllTask() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> list = session.createQuery(
                "from Task", Task.class).list();
        session.close();
        return list;
    }

    private List<Task> getByCondDone(boolean cond) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> list = session.createQuery(
                "from Task where done = :fDone", Task.class)
                .setParameter("fDone", cond).list();
        session.close();
        return list;
    }

    public List<Task> getDone() {
        return getByCondDone(true);
    }

    public List<Task> getNew() {
        return getByCondDone(false);
    }
}
