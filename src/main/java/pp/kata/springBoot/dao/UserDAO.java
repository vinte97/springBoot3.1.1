package pp.kata.springBoot.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pp.kata.springBoot.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> showAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public User getUserByUd(long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
    }
    public void update(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(long id) {
        entityManager.remove(getUserByUd(id));
    }
}
