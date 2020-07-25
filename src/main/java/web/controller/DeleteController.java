package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class DeleteController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String getDeletePage() {
        return "deleteUser";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String getDeleteUser(@RequestParam Long id, Model model1) {
        service.deleteUser(id);
        List<User> users = service.getListUsers();
        model1.addAttribute("users", users);
        return "showUsers";
    }

}
