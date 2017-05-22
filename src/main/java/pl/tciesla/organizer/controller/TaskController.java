package pl.tciesla.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tciesla.organizer.form.TaskForm;
import pl.tciesla.organizer.model.Task;
import pl.tciesla.organizer.repository.TaskRepository;

import java.util.List;

@Controller
public class TaskController {

    private final String REDIRECT = "redirect:/tasks";
    private final String TASK_FORM_ATTRIBUTE = "taskForm";

    @Autowired
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

}
