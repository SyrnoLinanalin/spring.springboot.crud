package com.akhtyamovfanil.springboot.demo.controller;

import com.akhtyamovfanil.springboot.demo.model.User;

import com.akhtyamovfanil.springboot.demo.repository.UserRepository;
import com.akhtyamovfanil.springboot.demo.service.RoleService;
import com.akhtyamovfanil.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        Optional<User> user = userRepository.getUserByUsername(principal.getName());
        model.addAttribute("user", user.get());
        return "userTry";
    }


    @GetMapping(value = "/admin")
    public String allUsers(Model model) {
        List<User> list = userService.allUsers();
        model.addAttribute("allUsers", list);
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roleService.getRoleSet());
        return "admin";
    }
   /* @GetMapping(value = "/new")
    public ModelAndView newUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("rolesList", roleService.getRoleSet());
        return modelAndView;
    }*/

    @PostMapping(value = "/admin")
    public String newUserPost(@ModelAttribute("user") User user,
                              @RequestParam("selectedRoles") String[] selectedRoles) {
        userService.save(user, selectedRoles);
        return "redirect:/admin";
    }

  /*  @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }*/

  /*  @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("rolesList", roleService.getRoleSet());
        return "edit";
    }*/

    @PatchMapping(value = "/{id}")
    public String editUser(@ModelAttribute("user") User user, HttpServletRequest req) {
        String[] selectedRoles = req.getParameterValues("selectedRoles");
        userService.save(user, selectedRoles);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }


}