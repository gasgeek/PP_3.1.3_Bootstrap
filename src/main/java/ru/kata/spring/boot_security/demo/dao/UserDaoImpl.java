package ru.kata.spring.boot_security.demo.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
/*

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
*/

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from " + User.class.getSimpleName(), User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        User user = entityManager.createQuery("SELECT u FROM User u join fetch u.roles where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return user;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}