package pl.tciesla.organizer.repository;

import org.junit.Test;
import pl.tciesla.organizer.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskRepositoryMockTest {

    @Test
    public void should_mock_repository_contains_5_example_tasks() {
        // given
        TaskRepository taskRepository = new TaskRepositoryMock();
        // when
        List<Task> tasks = taskRepository.findAll();
        // then
        assertThat(tasks).hasSize(5);
    }

    @Test
    public void should_remove_task_by_uuid() {
        // given
        TaskRepository taskRepository = new TaskRepositoryMock();
        Task exampleTask = taskRepository.findAll().stream().findAny().get();
        // when
        taskRepository.delete(exampleTask.getUuid());
        // then
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(4);
        assertThat(tasks.stream().anyMatch(task -> task.getUuid().equals(exampleTask.getUuid()))).isFalse();
    }

}