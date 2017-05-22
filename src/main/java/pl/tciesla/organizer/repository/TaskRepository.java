package pl.tciesla.organizer.repository;

import pl.tciesla.organizer.model.Task;

import java.util.List;

public interface TaskRepository {

    Long nextId();
    List<Task> findAll();
    void save(Task task);
    void delete(Long taskId);
}
