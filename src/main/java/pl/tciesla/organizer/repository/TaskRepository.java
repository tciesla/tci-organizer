package pl.tciesla.organizer.repository;

import pl.tciesla.organizer.model.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();
}
