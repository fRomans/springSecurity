package com.javamaster.service;

import com.javamaster.model.User;


import java.util.List;

public interface UserService {

    List<User> getListUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserById(long id);
   // void deleteUser(Long id);

}
