package pl.tciesla.organizer.repository;

import pl.tciesla.organizer.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    String nextUuid();
    List<Task> findAll();
    Optional<Task> find(String taskUuid);
    void save(Task task);
    void delete(String taskUuid);
}
