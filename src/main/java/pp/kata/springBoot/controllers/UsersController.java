package pp.kata.springBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pp.kata.springBoot.dao.UserDAO;
import pp.kata.springBoot.models.User;


import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userDAO.showAllUsers());
        return "users/list";
    }

    @GetMapping("/show")
    public String show(@RequestParam(name = "id") long id, Model model) {
        model.addAttribute("user", userDAO.getUserByUd(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") long id) {
        model.addAttribute("user", userDAO.getUserByUd(id));
        return "users/editUser";
    }


    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/editUser";
        }
        userDAO.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "id") long id) {
        userDAO.deleteUser(id);
        return "redirect:/users";
    }

}
