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

    public List<Task> getAllTask() {
        return crudRepository.query("from Task", Task.class);
    }

    private List<Task> getByCondDone(boolean cond) {
        return crudRepository.query("from Task where done = :fDone",
                Task.class,
                Map.of("fDone", cond));
    }

    public List<Task> getDone() {
        return getByCondDone(true);
    }

    public List<Task> getNew() {
        return getByCondDone(false);
    }
}
