package com.javamaster.controller;

import com.javamaster.model.User;
import com.javamaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DeleteController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDeleteGet() {
        return "deleteUser";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String getDeletePost(@RequestParam Long id, Model model1) {
        service.deleteUser(id);
        List<User> users = service.getListUsers();
        model1.addAttribute("users", users);
        return "showUsers";
    }

}
