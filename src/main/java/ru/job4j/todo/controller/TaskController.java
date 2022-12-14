package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.exception.NoSpecifyCategory;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.util.GetUserView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final CategoryService categoryService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        User user = (User) session.getAttribute("user");
        model.addAttribute("allTasks", taskService.getAll(user.getId()));
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/allTasks")
    public String allTasks(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        User user = (User) session.getAttribute("user");
        model.addAttribute("allTasks", taskService.getAll(user.getId()));
        model.addAttribute("user", user);
        return "allTasksForm";
    }

    @GetMapping("/allNewTasks")
    public String allNewTasks(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        User user = (User) session.getAttribute("user");
        model.addAttribute("newTasks", taskService.getNew(user.getId()));
        model.addAttribute("user", user);
        return "allNewTasksForm";
    }

    @GetMapping("/allDoneTasks")
    public String allDoneTasks(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        User user = (User) session.getAttribute("user");
        model.addAttribute("doneTasks", taskService.getDone(user.getId()));
        model.addAttribute("user", user);
        return "allDoneTasksForm";
    }

    @GetMapping("/addTask")
    public String addTaskForm(Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        model.addAttribute("priorities", taskService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "addTaskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task,
                          @RequestParam(name = "categoryId", required = false) List<Integer> categoriesId,
                          Model model,
                          HttpSession session) {
        GetUserView.getUserView(model, session);
        if (categoriesId == null) {
            throw new NoSpecifyCategory("Category is not specified");
        }
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        task.setCategories(categoryService.findSomeCategoriesById(categoriesId));
        taskService.add(task);
        return "redirect:index";
    }

    @GetMapping("/editTask/{taskID}")
    public String updateTask(@PathVariable("taskID") int taskID,
                             Model model,
                             HttpSession session) {
        GetUserView.getUserView(model, session);
        Optional<Task> optionalTask = taskService.findById(taskID);
        model.addAttribute("editTask", optionalTask.get());
        model.addAttribute("priorities", taskService.findAll());
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
        model.addAttribute("successMessage", "???????????? ?????????????????? ??????????????");
        return "successPage";
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        GetUserView.getUserView(model, session);
        taskService.delete(taskID);
        model.addAttribute("successMessage", "???????????? ?????????????? ??????????????");
        return "successPage";
    }
}
