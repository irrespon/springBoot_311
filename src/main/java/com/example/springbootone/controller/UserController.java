package com.example.springbootone.controller;

import com.example.springbootone.model.User;
import com.example.springbootone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String allUsers(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "editPage";
    }

    @GetMapping("/newUser")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        return "newUser";

    }

//    @PostMapping()
//    public String createUser(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return "redirect:/";
//    }

    @PostMapping()
    public RedirectView createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return new RedirectView("/");
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/";
    }

    @PostConstruct
    public void addToDBUser(){
        userService.add(new User("Иван", "Иванов"));
        userService.add(new User("Петр", "Петров"));
        userService.add(new User("Сидр", "Сидоров"));
        userService.add(new User("Иван", "Грозный"));
    }

}