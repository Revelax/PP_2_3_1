package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT users FROM User users", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        if (entityManager.find(User.class, id) == null) {
            throw new EntityNotFoundException("Entity not found for given ID " + id);
        }
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUserById(int id) {
        if (entityManager.find(User.class, id) == null) {
            throw new EntityNotFoundException("Entity not found for given ID " + id);
        }
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void editUser(int id, User userNew) {
        if (entityManager.find(User.class, id) == null) {
            throw new EntityNotFoundException("Entity not found for given ID " + id);
        }
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setName(userNew.getName());
            user.setLastname(userNew.getLastname());
            user.setAge(userNew.getAge());
        }
    }
}
