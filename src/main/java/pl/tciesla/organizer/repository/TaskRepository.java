package pl.tciesla.organizer.repository;

import pl.tciesla.organizer.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Long nextId();
    List<Task> findAll();
    Optional<Task> find(Long taskId);
    void save(Task task);
    void delete(Long taskId);
}
