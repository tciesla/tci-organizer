package pl.tciesla.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import pl.tciesla.organizer.form.TaskForm;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;
import pl.tciesla.organizer.repository.TaskRepositoryXml;

import java.util.List;
import java.util.Optional;

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
        TaskForm taskForm = new TaskForm();
        model.addAttribute("taskForm", taskForm);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute(TASK_FORM_ATTRIBUTE) TaskForm taskForm) {
        String nextUuid = taskRepository.nextUuid();
        Task task = new Task(nextUuid, taskForm.getName());
        taskRepository.save(task);
        return REDIRECT;
    }

    @DeleteMapping("/tasks/{taskUuid}")
    public String deleteTask(@PathVariable String taskUuid) {
        taskRepository.delete(taskUuid);
        return REDIRECT;
    }

    @PutMapping("/tasks/{taskUuid}/prioritize/{votes}")
    public String prioritize(@PathVariable String taskUuid, @PathVariable Integer votes) {
        Optional<Task> taskOptional = taskRepository.find(taskUuid);
        taskOptional.ifPresent(task -> task.prioritize(votes));
        taskOptional.ifPresent(taskRepository::save);
        return REDIRECT;
    }

    @PutMapping("/tasks/{taskUuid}/complete")
    public String completeTask(@PathVariable String taskUuid) {
        Optional<Task> taskOptional = taskRepository.find(taskUuid);
        taskOptional.ifPresent(Task::complete);
        taskOptional.ifPresent(taskRepository::save);
        return REDIRECT;
    }

}
