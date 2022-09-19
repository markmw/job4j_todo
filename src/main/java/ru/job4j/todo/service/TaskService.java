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

    public Optional<Integer> executeDone(int id) {
        Optional<Integer> result;
        int res = taskStore.executeDone(id);
        if (res != 0) {
            result = Optional.of(res);
        } else {
            result = Optional.empty();
        }
        return result;
    }
    public Optional<Integer> delete(int id) {
        Optional<Integer> result;
        int res = taskStore.delete(id);
        if (res != 0) {
            result = Optional.of(res);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    public int update(Task task) {
        return taskStore.update(task);
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
