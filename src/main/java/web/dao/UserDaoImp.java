package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("select  u from User u");
        return query.getResultList();
    }


    @Override
    public User findByUsername(String name) {
        String sql = "select u from User u where u.name= :paramName";

        User user = (User) entityManager.createQuery(sql)
                .setParameter("paramName", name)
                .getSingleResult();

        return user;

    }

    @Override
    public User getUserById(long id) {
        User user1 =  entityManager.find(User.class, id);
        return user1;
    }

    @Override
    public void deleteUser(Long id) {
        User user2 = getUserById(id);
        entityManager.remove(user2);
    }

    @Override
    public void updateUser(User user) {

        entityManager.merge(user);
       // this.entityManager.refresh(this.entityManager.merge(user));
    }

    @Override
    public User getUserByNameAndPassword(String name,String password){
        String sql = "select u from User u where u.name= :paramName and u.password=:paramPassword";

        User user = (User) entityManager.createQuery(sql)
                .setParameter("paramName", name)
                .setParameter("paramPassword",password)
                .getSingleResult();

        return user;
    }


}
