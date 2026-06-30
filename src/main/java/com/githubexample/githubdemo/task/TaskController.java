package com.githubexample.githubdemo.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String filter, Model model) {
        Priority priority = (filter != null && !filter.isEmpty()) ? Priority.valueOf(filter) : null;
        List<Task> tasks = priority != null ? taskService.findByPriority(priority) : taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("filter", priority);
        return "tasks/index";
    }

    @PostMapping("/{id}/priority")
    public String updatePriority(@PathVariable Long id, @RequestParam Priority priority,
                                 @RequestParam(required = false) String filter) {
        taskService.updatePriority(id, priority);
        return (filter != null && !filter.isEmpty()) ? "redirect:/tasks?filter=" + filter : "redirect:/tasks";
    }
}
