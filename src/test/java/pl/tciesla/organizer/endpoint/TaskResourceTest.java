package pl.tciesla.organizer.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskResourceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskResource taskResource;

    @Test
    public void should_return_all_tasks_from_repository() throws Exception {
        // given
        Task exampleTask1 = new Task("uuid1", "some task 1");
        Task exampleTask2 = new Task("uuid2", "some task 2");
        List<Task> exampleTasks = List.of(exampleTask1, exampleTask2);
        when(taskRepository.findAll()).thenReturn(exampleTasks);
        // when
        List<Task> tasks = taskResource.tasks();
        // then
        assertThat(tasks).isEqualTo(exampleTasks);
    }

}