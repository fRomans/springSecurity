package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/admin")
public class AdminController extends HttpServlet {


    private UserService service;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(PasswordEncoder passwordEncoder, UserService service) {
        this.passwordEncoder = passwordEncoder;
        this.service = service;
    }

    @RequestMapping //url показа usera  в приложении(может не совпадать с url запуска сервера)
    public String getIndex(Model model) {
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getPage() {
        return "addUser";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, @RequestParam(value = "role_id") Set<Role> role) {
        user.setRoles(role);

        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        service.addUser(user);
        return "redirect:/admin";//todo   привести  к такому виду!!!/
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDeletePage(@RequestParam(value = "deleteId") Long id, Model model) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String getDeleteUser(@RequestParam(value = "deleteId") Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getPage(@RequestParam(value = "updataId") Long id, Model model) {
        boolean adminTrue;
        boolean userTrue;
        User user = service.getUserById(id);
        for (Role role : user.getAuthorities()) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                adminTrue = true;
                model.addAttribute("adminTrueAttr", adminTrue);
            } else if (role.getAuthority().equals("ROLE_USER")) {
                userTrue = true;
                model.addAttribute("userTrueAttr", userTrue);
            }
        }
        model.addAttribute("user", user);
        return "updateUser";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String getUpdateUser(@ModelAttribute User user, @RequestParam Set<Role> role) {
        User userUpdate = service.getUserById(user.getId());
        userUpdate.setName(user.getUsername());
        if (!user.getPassword().equals("")) {
            userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userUpdate.setMoney(user.getMoney());
        userUpdate.setRoles(role);
        service.updateUser(userUpdate);
        return "redirect:/admin";
    }


}

