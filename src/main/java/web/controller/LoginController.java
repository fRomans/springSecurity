package web.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @RequestMapping("/login") //url показа usera  в приложении(может не совпадать с url запуска сервера)
    public String getIndex(@ModelAttribute User user, Model model) {
        List<User> users = service.getListUsers();
        String resultPage = null;
        for (User user1 : users) {
            if (user.equals(user1)) {
                for (Role role : user1.getRoles()) {
                    if (role.equals("ADMIN")) {
                        model.addAttribute("users", users);
                        resultPage = "showUsers";//todo страница url /admin
                    } else {
                        model.addAttribute("password", user.getPassword());
                        model.addAttribute("login", user.getName());
                        model.addAttribute("role", "USER");
                        resultPage = "userView";
                    }
                }
            } else {
                System.out.println("Нет такого user-а");
                resultPage = "login";//todo выводим страницу login если нет такого юзера в базе
            }
        }

        return resultPage;
    }

    @RequestMapping("/welcome")
    public String getWelcome() {
        return "index";
    }
}
