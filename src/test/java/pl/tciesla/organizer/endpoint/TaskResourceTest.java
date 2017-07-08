package pl.tciesla.organizer.endpoint;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;

import java.util.ArrayList;
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
        Task exampleTask1 = new Task(1L, "some task 1");
        Task exampleTask2 = new Task(2L, "some task 2");
        ArrayList<Task> exampleTasks = Lists.newArrayList(exampleTask1, exampleTask2);
        when(taskRepository.findAll()).thenReturn(exampleTasks);
        // when
        List<Task> tasks = taskResource.tasks();
        // then
        assertThat(tasks).isEqualTo(exampleTasks);
    }

}