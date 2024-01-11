package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("people", userService.listUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("users") User user) {
        return "new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("users") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", userService.listUserId(id));
        return "show";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", userService.listUserId(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@RequestParam("id") int id,
                         @ModelAttribute("person") @Valid User newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.editUser(id, newUser);
        return "redirect:/";
    }
}
