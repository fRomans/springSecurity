package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService service;

    @RequestMapping("/") //url показа usera  в приложении(может не совпадать с url запуска сервера)
    public String getIndex(Model model){
        List<User>users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @RequestMapping("/welcome")
    public String getWelcome(){
        return "index";
    }
}
