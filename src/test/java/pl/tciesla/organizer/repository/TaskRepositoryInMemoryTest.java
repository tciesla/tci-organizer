package pl.tciesla.organizer.repository;

import org.junit.Test;
import pl.tciesla.organizer.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskRepositoryInMemoryTest {

    @Test
    public void should_repository_contains_5_example_tasks() throws Exception {
        // given
        TaskRepositoryInMemory taskRepository = new TaskRepositoryInMemory();
        // when
        List<Task> tasks = taskRepository.findAll();
        // then
        assertThat(tasks).hasSize(5);
    }

}