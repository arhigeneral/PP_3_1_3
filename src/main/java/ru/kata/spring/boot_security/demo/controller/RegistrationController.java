package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("")
    public String createUser(User user){
        User userFromDb = userService.findByUsername(user.getUsername());
        if(userFromDb == null){
            userService.save(user);
            return "redirect:/login";
        } else {
            return "user-create";
        }
    }
}
