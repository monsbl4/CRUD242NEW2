package ru.denisov.spring.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denisov.spring.Services.UserServiceImp;
import ru.denisov.spring.models.User;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserServiceImp userServiceImp;
    @Autowired
    public UsersController(UserServiceImp userServiceImp1) {
        this.userServiceImp = userServiceImp1;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        // Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("users", userServiceImp.index());
        return "users/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получим страницу пользователя по ID из DAO и передадим на отображение в представление
        model.addAttribute("user",userServiceImp.show(id));
        return "users/show";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        //@Valid - проверяет на правильность заполнения, указанных в Users
        //bindingResult - ошибки, идет после User всегда
        if (bindingResult.hasErrors()){
            return "users/new";
        }
        userServiceImp.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userServiceImp.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user")@Valid User user,BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "users/edit";
        }
        userServiceImp.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userServiceImp.delete(id);
        return "redirect:/users";
    }
}
