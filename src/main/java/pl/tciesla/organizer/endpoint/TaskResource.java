package pl.tciesla.organizer.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class TaskResource {

    @Autowired @Qualifier("taskRepositoryXml")
    private TaskRepository taskRepository;

    @RequestMapping(value = "/tasks", method = GET)
    public List<Task> tasks() {
        return taskRepository.findAll();
    }

    @RequestMapping(value = "/tasks/{id}", method = GET)
    public Task task(@PathVariable long id) {
        return taskRepository.find(id).orElse(null);
    }

    @RequestMapping(value = "/tasks", method = POST)
    public Task saveTask(@RequestParam String name) {
        Task task = new Task(taskRepository.nextId(), name);
        taskRepository.save(task);
        return task;
    }

    @RequestMapping(value = "/tasks/{id}", method = DELETE)
    public void deleteTask(@PathVariable long id) {
        taskRepository.delete(id);
    }

}
