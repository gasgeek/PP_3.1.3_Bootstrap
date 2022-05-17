package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService service;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Principal principal,
                        Model model) {
        User user = service.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", service.findAll());
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/admin";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles") String[] roles) {
        System.out.println(user.getFirstName()+user.getLastName()+user.getPassword());
        user.setRoles(roleService.getSetOfRoles(roles));
        System.out.println(user.getRoles());
        service.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("user") User user,
                       Model model,
                       @PathVariable("id") Long id,
                       @RequestParam(value = "editRoles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", service.findById(id));
        return "users/admin";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles", required = false) String[] roles) {

        user.setRoles(roleService.getSetOfRoles(roles));
        service.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/admin";
    }
}