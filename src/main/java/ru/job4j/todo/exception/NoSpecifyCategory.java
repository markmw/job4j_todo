package ru.job4j.todo.exception;

public class NoSpecifyCategory extends RuntimeException {
    public NoSpecifyCategory(String message) {
        super(message);
    }
}
