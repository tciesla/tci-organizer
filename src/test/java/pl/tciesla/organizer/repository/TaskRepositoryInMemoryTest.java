package pl.tciesla.organizer.repository;

import org.junit.Test;
import pl.tciesla.organizer.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskRepositoryInMemoryTest {

    @Test
    public void should_repository_contains_5_example_tasks() throws Exception {
        // given
        TaskRepository taskRepository = new TaskRepositoryInMemory();
        // when
        List<Task> tasks = taskRepository.findAll();
        // then
        assertThat(tasks).hasSize(5);
    }

    @Test
    public void should_remove_task_with_id_2() throws Exception {
        // given
        TaskRepository taskRepository = new TaskRepositoryInMemory();
        // when
        taskRepository.delete(2L);
        // then
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(4);
        assertThat(tasks.stream().anyMatch(task -> task.getId() == 2L)).isFalse();
    }

    @Test
    public void should_return_1_as_initial_id() throws Exception {
        // given
        TaskRepository taskRepository = new TaskRepositoryInMemory();
        taskRepository.delete(1L);
        taskRepository.delete(2L);
        taskRepository.delete(3L);
        taskRepository.delete(4L);
        taskRepository.delete(5L);
        // when
        Long id = taskRepository.nextId();
        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void should_return_6_as_next_id() throws Exception {
        // given
        TaskRepository taskRepository = new TaskRepositoryInMemory();
        // when
        Long id = taskRepository.nextId();
        // then
        assertThat(id).isEqualTo(6);
    }

}