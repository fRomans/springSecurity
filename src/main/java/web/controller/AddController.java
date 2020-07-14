package web.controller;

import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import java.util.List;


@Controller
public class AddController extends HttpServlet {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getPage() {
            return "addUser";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model) {
        service.addUser(user);
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }



}

