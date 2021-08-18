package ru.denisov.spring.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.denisov.spring.Services.UserServiceImp;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserServiceImp userServiceImp;
    @GetMapping("/{username}")
    public String show(@PathVariable("username") String username, Model model){
        model.addAttribute("user",userServiceImp.getUserByUsername(username));
        return "users/user";
    }
}
