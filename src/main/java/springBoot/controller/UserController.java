package springBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springBoot.model.User;
import springBoot.service.UserService;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String getAllUsers(Model model) {
        var users = service.getAllUsers();
        model.addAttribute("allUsers", users);
        return "/show-users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        System.out.println("user addUser");
        return "info-user";
    }

    @PostMapping("")
    public String saveUser(@ModelAttribute("user") User user) {

            service.addUser(user);
            System.out.println("saveuser");


        return "redirect:/";
    }

    @PatchMapping("edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println("update");
        service.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String returnUser(@PathVariable("id") int id, Model model) {
        User user = service.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            System.out.println(user.getId());
        } else {
            System.out.println("user not exist returnUser");
            return "redirect:/";
        }
        return "update-user";

    }

    @DeleteMapping ("delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        if (user != null) {
            System.out.println("postDeleteUser");
            service.deleteUser(id);
            System.out.println("deleteUser");
        } else {
            System.out.println("user not exist deleteUser");
            return "redirect:/";
        }
        return "redirect:/";
    }
}
