package pl.tciesla.organizer.repository;

import org.junit.Before;
import org.junit.Test;
import pl.tciesla.organizer.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskRepositoryInMemoryTest {

    TaskRepositoryInMemory taskRepository;

    @Before
    public void setUp() throws Exception {
        taskRepository = new TaskRepositoryInMemory();
    }

    @Test
    public void should_repository_contains_5_example_tasks() throws Exception {
        // when
        List<Task> tasks = taskRepository.findAll();
        // then
        assertThat(tasks).hasSize(5);
    }

    @Test
    public void should_remove_task_with_id_2() throws Exception {
        // when
        taskRepository.delete(2L);
        // then
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(4);
        assertThat(tasks.stream().anyMatch(task -> task.getId() == 2L)).isFalse();
    }
}