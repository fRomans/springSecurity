package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UserViewController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserPage(Authentication authentication, Model model) {
        for (GrantedAuthority role : authentication.getAuthorities()) {
            if (!role.getAuthority().equals("ROLE_USER")) {
                System.out.println("Без \"ROLE_USER\" нет доступа");
                return "login";
            } else {
                User user = (User) authentication.getPrincipal();
                model.addAttribute("user22", user);
            }
        }
        return "user";
    }
}
