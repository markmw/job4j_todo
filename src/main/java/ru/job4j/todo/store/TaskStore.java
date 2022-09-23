package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final CrudRepository crudRepository;

    public void executeDone(int id) {
        crudRepository.run("update Task set done = :fDone where id = :fId",
                Map.of("fDone", true, "fId", id));
    }

    public void delete(int id) {
        crudRepository.run("delete from Task where id = :fId",
                Map.of("fId", id));
    }

    public void update(Task task) {
        crudRepository.run(session -> session.createQuery(
                "update Task set description = :fDescription, done = :fDone where id = :fId")
                .setParameter("fDescription", task.getDescription())
                .setParameter("fDone", task.isDone())
                .setParameter("fId", task.getId())
                .executeUpdate());
    }

    public Optional<Task> findById(int id) {
        return crudRepository.optional("from Task where id = :fId",
                Task.class,
                Map.of("fId", id));
    }

    public Task add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    public List<Task> getAllTask(int user_id) {
        return crudRepository.query("from Task where user_id = :fUser_id",
                Task.class,
                Map.of("fUser_id", user_id));
    }

    private List<Task> getByCondDone(boolean cond, int user_id) {
        return crudRepository.query("from Task where done = :fDone and user_id = :fUser_id",
                Task.class,
                Map.of("fDone", cond, "fUser_id", user_id));
    }

    public List<Task> getDone(int user_id) {
        return getByCondDone(true, user_id);
    }

    public List<Task> getNew(int user_id) {
        return getByCondDone(false, user_id);
    }
}
