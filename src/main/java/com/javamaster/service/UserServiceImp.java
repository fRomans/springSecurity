package com.javamaster.service;

import com.javamaster.dao.UserDao;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void addUser(User user) {
      userDao.addUser(user);
   }

   @Transactional
   @Override
   public User getUserById(long id) {
      User user = userDao.getUserById(id);
      return user;
   }
   @Transactional(readOnly = true)
   @Override
   public List<User> getListUsers() {
      return userDao.getListUsers();
   }


   @Transactional
   @Override
   public void deleteUser(Long id)  {
      userDao.deleteUser(id);

   }

   @Transactional
   public void updateUser(User user)  {
      userDao.updateUser(user);
   }




}
