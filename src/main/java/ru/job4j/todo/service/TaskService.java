package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskStore taskStore;

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

    public List<Task> getAll() {
        return taskStore.getAllTask();
    }

    public List<Task> getDone() {
        return taskStore.getDone();
    }

    public List<Task> getNew() {
        return taskStore.getNew();
    }
}
