package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

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
    public String create(@ModelAttribute("users") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", userService.listUserId(id));
        return "show";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", userService.listUserId(id));
        return "edit";
    }

    @PatchMapping("/edit")
    public String update(@RequestParam("id") int id, @ModelAttribute("person") User newUser) {
        userService.editUser(id, newUser);
        return "redirect:/";
    }
}
