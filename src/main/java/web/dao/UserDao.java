package web.dao;

import org.springframework.data.repository.CrudRepository;
import web.model.User;

import java.util.List;

public interface UserDao {

   List<User> getListUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserById(long id);
    User findByUsername(String name);
    User getUserByNameAndPassword(String name,String password);
}
