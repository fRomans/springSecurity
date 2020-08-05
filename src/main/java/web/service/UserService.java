package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;
import java.util.List;

public interface UserService   {

    List<User> getListUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserById(long id);
    User getUserByNameAndPassw(String name,String password);
    //UserDetails loadUserByUsername(String username);
    // void deleteUser(Long id);

}
