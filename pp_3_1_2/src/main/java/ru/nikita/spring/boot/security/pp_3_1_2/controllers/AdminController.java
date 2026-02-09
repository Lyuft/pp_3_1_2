package ru.nikita.spring.boot.security.pp_3_1_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(Model model){

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "admin/all-users";
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(Model model){

        model.addAttribute("user", new User());

        return "admin/user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") List<String> roleName){

        userService.saveUser(user,roleName);

        return "redirect:/admin/";
    }

    @GetMapping(value = "/updateInfo")
    public String updateUser(@RequestParam("id") int id, Model model){

        User user = userService.getUser(id);
        model.addAttribute("user",user);

        return "admin/user-info";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") int id){

        userService.deleteUser(id);

        return "redirect:/admin/";
    }
}
