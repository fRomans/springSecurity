package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class ShowUsersController {

    @Autowired
    private UserService service;
    @Autowired
    private Role role;

    @RequestMapping("/admin") //url показа usera  в приложении(может не совпадать с url запуска сервера)
    public String getIndex(Model model) {
        //,Authentication  authentication
//                                              //костыль для добавления роли еще одной
//        User user = (User) authentication.getPrincipal();
//        Set<Role> roles = (Set<Role>) user.getAuthorities();
//        role.setRole("ROLE_ADMIN");
//        roles.add(role);
//        user.setRoles(roles);
//        service.updateUser(user);
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @RequestMapping("/welcome")
    public String getWelcome(){
        return "index";
    }
}
