package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.TimeZone;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TimeZoneStore {
    private final CrudRepository crudRepository;

    public List<TimeZone> findAll() {
        return crudRepository.query("from TimeZone", TimeZone.class);
    }

    public Optional<TimeZone> findById(int id) {
        return crudRepository.optional("from TimeZone where id = :fId", TimeZone.class,
                Map.of("fId", id));
    }
}
