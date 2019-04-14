package pl.tciesla.organizer.repository;

import org.springframework.stereotype.Repository;
import pl.tciesla.organizer.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryMock implements TaskRepository {

    private Map<Long, Task> tasks = new ConcurrentHashMap<>();

    TaskRepositoryMock() {
        tasks.put(1L, new Task(1L, "Example task #1"));
        tasks.put(2L, new Task(2L, "Example task #2"));
        tasks.put(3L, new Task(3L, "Example task #3"));
        tasks.put(4L, new Task(4L, "Example task #4"));
        tasks.put(5L, new Task(5L, "Example task #5"));
    }

    @Override
    public Long nextId() {
        OptionalLong previousId = tasks.keySet().stream().mapToLong(k -> k).max();
        return previousId.isPresent() ? previousId.getAsLong() + 1 : 1L;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Optional<Task> find(Long taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }

    @Override
    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void delete(Long taskId) {
        tasks.remove(taskId);
    }

}
