package pl.tciesla.organizer.repository;

import org.springframework.stereotype.Repository;
import pl.tciesla.organizer.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@Repository
public class TaskRepositoryMock implements TaskRepository {

    private Map<String, Task> tasks = new ConcurrentHashMap<>();

    TaskRepositoryMock() {
        IntStream.range(1, 6)
                .mapToObj(i -> new Task(nextUuid(), "example task #" + i))
                .forEach(task -> tasks.put(task.getUuid(), task));
    }

    @Override
    public String nextUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Optional<Task> find(String taskUuid) {
        return Optional.ofNullable(tasks.get(taskUuid));
    }

    @Override
    public void save(Task task) {
        tasks.put(task.getUuid(), task);
    }

    @Override
    public void delete(String taskUuid) {
        tasks.remove(taskUuid);
    }

}
