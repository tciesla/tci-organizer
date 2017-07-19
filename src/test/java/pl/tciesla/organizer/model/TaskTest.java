package pl.tciesla.organizer.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

public class TaskTest {

    private final long id = 1L;
    private final String name = "name";

    @Test(expected = NullPointerException.class)
    public void should_throw_exception_when_id_is_null() throws Exception {
        // when
        new Task(null, name);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_exception_when_name_is_null() throws Exception {
        // when
        new Task(id, null);
    }

    @Test
    public void should_initialize_task_id() throws Exception {
        // when
        Task task = new Task(id, name);
        // then
        assertThat(task.getId()).isEqualTo(id);
    }

    @Test
    public void should_initialize_task_name() throws Exception {
        // when
        Task task = new Task(id, name);
        // then
        assertThat(task.getName()).isEqualTo(name);
    }

    @Test
    public void should_create_task_with_new_status() throws Exception {
        // when
        Task task = new Task(id, name);
        // then
        assertThat(task.getStatus()).isEqualTo(Task.Status.NEW);
    }

    @Test
    public void should_create_task_with_zero_priority() throws Exception {
        // when
        Task task = new Task(id, name);
        // then
        assertThat(task.getPriority()).isEqualTo(0);
    }

    @Test
    public void should_initialize_task_created_date() throws Exception {
        // when
        Task task = new Task(id, name);
        // then
        assertThat(task.getCreated()).isNotNull();
    }

    @Test
    public void should_initialized_task_be_not_important() throws Exception {
        // when
        Task task = new Task(id, name);
        // then
        assertThat(task.isImportant()).isFalse();
    }

    @Test
    public void should_tag_task_as_important() throws Exception {
        // given
        Task task = new Task(id, name);
        // when
        task.toggleImportant();
        // then
        assertThat(task.isImportant()).isTrue();
    }

    @Test
    public void should_complete_change_task_status_to_completed() throws Exception {
        // given
        Task task = new Task(id, name);
        assumeTrue(task.getStatus() == Task.Status.NEW);
        // when
        task.complete();
        // then
        assertThat(task.getStatus()).isEqualTo(Task.Status.COMPLETED);
    }

    @Test
    public void should_complete_initialize_finished_date() throws Exception {
        // given
        Task task = new Task(id, name);
        assumeFalse(task.getFinished().isPresent());
        // when
        task.complete();
        // then
        assertThat(task.getFinished().isPresent()).isTrue();
    }

    @Test
    public void should_add_five_votes_to_task_priority() throws Exception {
        // given
        Task task = new Task(id, name);
        int fiveVotes = 5;
        // when
        task.prioritize(fiveVotes);
        // then
        assertThat(task.getPriority()).isEqualTo(fiveVotes);
    }

}