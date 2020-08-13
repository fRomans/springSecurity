package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.Set;


@Controller
public class AddController extends HttpServlet {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String getPage() {
        return "addUser";
    }


    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, @RequestParam(value = "role_id") Set<Role> role
            , Model model) {
        user.setRoles(role) ;
        service.addUser(user);

        return "redirect:/admin";//todo   привести  к такому виду!!!/
    }


}

