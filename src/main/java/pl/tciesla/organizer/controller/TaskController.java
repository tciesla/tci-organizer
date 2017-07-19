package pl.tciesla.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tciesla.organizer.form.TaskForm;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;
import pl.tciesla.organizer.repository.TaskRepositoryXml;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Controller
public class TaskController {

    private final String REDIRECT = "redirect:/tasks";
    private final String TASK_FORM_ATTRIBUTE = "taskForm";

    @Autowired
    @Qualifier(TaskRepositoryXml.BEAN_NAME)
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String redirectToTasks() {
        return REDIRECT;
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);

        List<Task> importantTasks = tasks
                .stream()
                .filter(Task::isImportant)
                .collect(toList());
        model.addAttribute("importantTasks", importantTasks);

        TaskForm taskForm = new TaskForm();
        model.addAttribute("taskForm", taskForm);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute(TASK_FORM_ATTRIBUTE) TaskForm taskForm) {
        Long nextId = taskRepository.nextId();
        Task task = new Task(nextId, taskForm.getName());
        taskRepository.save(task);
        return REDIRECT;
    }

    @DeleteMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskRepository.delete(taskId);
        return REDIRECT;
    }

    @PutMapping("/tasks/{taskId}/prioritize/{votes}")
    public String prioritize(@PathVariable Long taskId, @PathVariable Integer votes) {
        Optional<Task> taskOptional = taskRepository.find(taskId);
        taskOptional.ifPresent(task -> task.prioritize(votes));
        taskOptional.ifPresent(taskRepository::save);
        return REDIRECT;
    }

    @PutMapping("/tasks/{taskId}/important")
    public String importantTask(@PathVariable Long taskId) {
        Optional<Task> taskOptional = taskRepository.find(taskId);
        taskOptional.ifPresent(Task::toggleImportant);
        taskOptional.ifPresent(taskRepository::save);
        return REDIRECT;
    }

    @PutMapping("/tasks/{taskId}/complete")
    public String completeTask(@PathVariable Long taskId) {
        Optional<Task> taskOptional = taskRepository.find(taskId);
        taskOptional.ifPresent(Task::complete);
        taskOptional.ifPresent(taskRepository::save);
        return REDIRECT;
    }

}
