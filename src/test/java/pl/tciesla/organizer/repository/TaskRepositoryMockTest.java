package pl.tciesla.organizer.repository;

import org.junit.Test;
import pl.tciesla.organizer.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskRepositoryMockTest {

    @Test
    public void should_repository_contains_5_example_tasks() {
        // given
        TaskRepository taskRepository = new TaskRepositoryMock();
        // when
        List<Task> tasks = taskRepository.findAll();
        // then
        assertThat(tasks).hasSize(5);
    }

    @Test
    public void should_remove_task_with_id_2() {
        // given
        TaskRepository taskRepository = new TaskRepositoryMock();
        // when
        taskRepository.delete(2L);
        // then
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(4);
        assertThat(tasks.stream().anyMatch(task -> task.getId() == 2L)).isFalse();
    }

    @Test
    public void should_return_1_as_initial_id() {
        // given
        TaskRepository taskRepository = new TaskRepositoryMock();
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
    public void should_return_6_as_next_id() {
        // given
        TaskRepository taskRepository = new TaskRepositoryMock();
        // when
        Long id = taskRepository.nextId();
        // then
        assertThat(id).isEqualTo(6);
    }

}