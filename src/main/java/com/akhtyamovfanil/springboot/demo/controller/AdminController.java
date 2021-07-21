package com.akhtyamovfanil.springboot.demo.controller;

import com.akhtyamovfanil.springboot.demo.model.User;
import com.akhtyamovfanil.springboot.demo.service.RoleService;
import com.akhtyamovfanil.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;
    private RoleService roleService;



    @GetMapping
    public String allUsers(Model model) {
      model.addAttribute("allUsersList", userService.allUsers());
        return "admin";
    }
    @GetMapping(value = "/new")
    public ModelAndView newUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new");
        modelAndView.addObject("user", new User());
        //modelAndView.addObject("rolesList", roleService.getRoleSet());
        return modelAndView;
    }

    @PostMapping(value = "")
    public String newUserPost(@ModelAttribute("user") User user,
                              @RequestParam("selectedRoles") String[] selectedRoles) {
        userService.save(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("rolesList", roleService.getRoleSet());
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String editUserPatch(@ModelAttribute("user") User user, HttpServletRequest req) {
        String[] selectedRoles = req.getParameterValues("selectRoles");
        userService.save(user, selectedRoles);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}