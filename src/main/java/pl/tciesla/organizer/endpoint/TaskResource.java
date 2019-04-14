package pl.tciesla.organizer.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskResource {

    @Autowired @Qualifier("taskRepositoryXml")
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> tasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{uuid}")
    public Task task(@PathVariable String uuid) {
        return taskRepository.find(uuid).orElse(null);
    }

    @PostMapping("/tasks")
    public Task saveTask(@RequestParam String name) {
        Task task = new Task(taskRepository.nextUuid(), name);
        taskRepository.save(task);
        return task;
    }

    @DeleteMapping(value = "/tasks/{uuid}")
    public void deleteTask(@PathVariable String uuid) {
        taskRepository.delete(uuid);
    }

}
