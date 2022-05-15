package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUserList(Model model) {
        List<User> users = userService.getListUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin")
    public String createUserFrom(User user, Model model) {
        model.addAttribute("user", user);
        List<User> users = userService.getListUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/admin")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping ("/admin/{id}/delete")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("users", user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}/edit")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}