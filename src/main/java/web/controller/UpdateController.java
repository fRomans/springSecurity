package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UpdateController {

    @Autowired
    private UserService service;


    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public String getPage(@RequestParam(value="updataId") Long id, Model model) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String getUpdateUser(@ModelAttribute User user, Model model) {
        User userUpdate = service.getUserById(user.getId());
        userUpdate.setName(user.getUsername());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setMoney(user.getMoney());
        service.updateUser(userUpdate);
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

}
