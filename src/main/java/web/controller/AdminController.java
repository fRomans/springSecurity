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
public class AdminController extends HttpServlet {

    @Autowired
    private UserService service;

    @RequestMapping("/admin") //url показа usera  в приложении(может не совпадать с url запуска сервера)
    public String getIndex(Model model) {
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String getPage() {
        return "addUser";
    }


    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, @RequestParam(value = "role_id") Set<Role> role) {
        user.setRoles(role) ;
        service.addUser(user);
        return "redirect:/admin";//todo   привести  к такому виду!!!/
    }
    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String getDeletePage(@RequestParam(value="deleteId") Long id, Model model1) {
        User user = service.getUserById(id);
        model1.addAttribute("user", user);
        return "deleteUser";
    }


    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String getDeleteUser(@RequestParam(value="deleteId") Long id, Model model1) {
        service.deleteUser(id);
        List<User> users = service.getListUsers();
        model1.addAttribute("users", users);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public String getPage(@RequestParam(value="updataId") Long id, Model model) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }


    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String getUpdateUser(@ModelAttribute User user, @RequestParam Set<Role> role
            , Model model) {

        user.setRoles(role);
        User userUpdate = service.getUserById(user.getId());
        userUpdate.setName(user.getUsername());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setMoney(user.getMoney());
        userUpdate.setRoles((Set<Role>) user.getAuthorities());
        service.updateUser(userUpdate);
        List<User> users = service.getListUsers();
        model.addAttribute("users", users);
        return "redirect:/admin";
    }


}

