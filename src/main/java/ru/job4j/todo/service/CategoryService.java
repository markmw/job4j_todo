package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.CategoryStore;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryStore categoryStore;

    public List<Category> findAll() {
        return categoryStore.findAll();
    }

    public Category findById(int id) {
        Optional<Category> category = categoryStore.findById(id);
        if (category.isEmpty()) {
            throw new NoSuchElementException("Category with such id does not exist");
        }
        return category.get();
    }

    public List<Category> findSomeCategoriesById(List<Integer> ids) {
        return categoryStore.findSomeCategoriesById(ids);
    }
}
