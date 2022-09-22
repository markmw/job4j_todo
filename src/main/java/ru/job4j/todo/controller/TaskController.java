package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.util.GetUserView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        model.addAttribute("allTasks", taskService.getAll());
        return "index";
    }

    @GetMapping("/allTasks")
    public String allTasks(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        model.addAttribute("allTasks", taskService.getAll());
        return "allTasksForm";
    }

    @GetMapping("/allNewTasks")
    public String allNewTasks(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        model.addAttribute("newTasks", taskService.getNew());
        return "allNewTasksForm";
    }

    @GetMapping("/allDoneTasks")
    public String allDoneTasks(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        model.addAttribute("doneTasks", taskService.getDone());
        return "allDoneTasksForm";
    }

    @GetMapping("/addTask")
    public String addTaskForm(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        return "addTaskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        taskService.add(task);
        return "redirect:index";
    }

    @GetMapping("/editTask/{taskID}")
    public String updateTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        Optional<Task> optionalTask = taskService.findById(taskID);
        model.addAttribute("editTask", optionalTask.get());
        return "editTaskPage";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task task, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        taskService.update(task);
        return "redirect:index";
    }

    @GetMapping("/tasks/{taskID}")
    public String taskPage(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        Optional<Task> optionalTask = taskService.findById(taskID);
        model.addAttribute("thisTask", optionalTask.get());
        return "taskPage";
    }

    @PostMapping("/executeTask/{taskID}")
    public String executeTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        taskService.executeDone(taskID);
        model.addAttribute("successMessage", "Задача выполнена успешна");
        return "successPage";
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        taskService.delete(taskID);
        model.addAttribute("successMessage", "Задача удалена успешно");
        return "successPage";
    }
}
