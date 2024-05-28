package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String findAll(ModelMap model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("eachUser", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "user-list";
    }



    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/save/user")
    public String createUser(User user){
        User userFromDb = userService.findByUsername(user.getUsername());
        if(userFromDb == null){
            userService.save(user);
        }
        return "redirect:/admin/users";
    }
    @PostMapping("/update")
    public String updateUser(User user){
        userService.save(user);
        return "redirect:/admin/users";
    }
}