package pl.tciesla.organizer.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
public class TaskResource {

    @Autowired @Qualifier("taskRepositoryXml")
    private TaskRepository taskRepository;

    @RequestMapping(value = "/tasks", method = GET)
    public List<Task> tasks() {
        return taskRepository.findAll();
    }

}
