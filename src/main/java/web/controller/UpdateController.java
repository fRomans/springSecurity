package web.controller;


import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UpdateController {

    @Autowired
    private UserService service;


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getPage(@RequestParam Long id, Model model) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String getUpdateUser(@ModelAttribute User user, Model model) {
        User userUpdate = service.getUserById(user.getId());
        userUpdate.setName(user.getName());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setMoney(user.getMoney());
        service.updateUser(userUpdate);
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

}
