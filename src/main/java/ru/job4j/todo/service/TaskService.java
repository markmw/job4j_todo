package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.PriorityStore;
import ru.job4j.todo.store.TaskStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskStore taskStore;
    private final PriorityStore priorityStore;

    public void executeDone(int id) {
        taskStore.executeDone(id);
    }
    public void delete(int id) {
        taskStore.delete(id);
    }

    public void update(Task task) {
        taskStore.update(task);
    }

    public Task add(Task task) {
        return taskStore.add(task);
    }

    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    public List<Task> getAll(int user_id) {
        return taskStore.getAllTask(user_id);
    }

    public List<Task> getDone(int user_id) {
        return taskStore.getDone(user_id);
    }

    public List<Task> getNew(int user_id) {
        return taskStore.getNew(user_id);
    }

    public List<Priority> findAll() {
        return priorityStore.findAll();
    }

    public Optional<Priority> findByIdPriority(int id) {
        return priorityStore.findByIdPriority(id);
    }
}
