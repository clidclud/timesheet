package ru.gb.timesheet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-detail";
        } else {
            return "redirect:/not-found";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user-register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        long newId = users.stream().mapToLong(User::getId).max().orElse(0L) + 1;
        user.setId(newId);
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "redirect:/users";
    }

    @GetMapping("/not-found")
    public String notFound() {
        return "not-found";
    }
}
