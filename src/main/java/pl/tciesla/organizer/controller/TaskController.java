package pl.tciesla.organizer.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tciesla.organizer.model.Task;

import java.util.List;

@Controller
public class TaskController {

    private final String TASKS_VIEW = "tasks";

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", findAllTasks());
        return TASKS_VIEW;
    }

    private List<Task> findAllTasks() {
        Task task1 = new Task(1L, "Create task repository");
        Task task2 = new Task(2L, "Remove redundant files");
        return Lists.newArrayList(task1, task2);
    }

}
