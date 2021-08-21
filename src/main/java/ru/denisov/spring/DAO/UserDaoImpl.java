package ru.denisov.spring.DAO;

import org.springframework.stereotype.Repository;
import ru.denisov.spring.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> index() {

        return entityManager.createQuery("from User",User.class).getResultList();
    }
    @Transactional
    public User show (int id) {
        return entityManager.find(User.class,id);
    }
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
    @Transactional
    public void update(int id, User updatedUser) { ;
        entityManager.merge(updatedUser);
        entityManager.flush();

    }
    @Transactional
    public void delete(int id) {
            entityManager.remove(show(id));
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> user = entityManager.createQuery(
                "select user from User user WHERE user.username=:username", User.class
        );
        return user.setParameter("username", username).getResultList().stream().findAny().orElse(null);
    }

}
