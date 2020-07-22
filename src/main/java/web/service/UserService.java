package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;


import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getListUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserById(long id);
    UserDetails loadUserByUsername(String username);
    // void deleteUser(Long id);

}
