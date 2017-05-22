package pl.tciesla.organizer.repository;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;
import pl.tciesla.organizer.model.Task;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class TaskRepositoryInMemory implements TaskRepository {

    private static List<Task> tasks = Lists.newArrayList();

    static {
        tasks.add(new Task(1L, "Example task #1"));
        tasks.add(new Task(2L, "Example task #2"));
        tasks.add(new Task(3L, "Example task #3"));
        tasks.add(new Task(4L, "Example task #4"));
        tasks.add(new Task(5L, "Example task #5"));
    }

    @Override
    public List<Task> findAll() {
        return Lists.newArrayList(tasks);
    }

    @Override
    public void delete(Long taskId) {
        tasks = tasks.stream()
                .filter(task -> !task.getId().equals(taskId))
                .collect(toList());
    }

}
