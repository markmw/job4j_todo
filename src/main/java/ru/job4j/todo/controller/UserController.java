package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.todo.model.TimeZone;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TimeZoneService;
import ru.job4j.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final TimeZoneService timeZoneService;

    @GetMapping("/formAddUser")
    public String formAddUser(Model model) {
        model.addAttribute("timezones", timeZoneService.findAll());
        return "addUser";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> regUser = userService.add(user);
        Optional<TimeZone> timeZone = timeZoneService.findById(Integer.parseInt(req.getParameter("tz.id")));
        timeZone.ifPresent(user::setTimeZone);
        if (regUser.isEmpty()) {
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/fail")
    public String failRegUser() {
        return "fail";
    }

    @GetMapping("success")
    public String successRegUser() {
        return "success";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByLoginAndPwd(user.getLogin(), user.getPassword());
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }
}
