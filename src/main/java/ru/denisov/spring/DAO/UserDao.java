package ru.denisov.spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.denisov.spring.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class UserDao implements UserDaoInt{
    @PersistenceContext
    private EntityManager entityManager;

//    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public List<User> index() {

        return entityManager.createNativeQuery("SELECT * FROM users",
                User.class).getResultList();
    }
    @Transactional
    public User show (int id) {
//        return jdbcTemplate.query("Select * from users where id=?", new Object[]{id},new BeanPropertyRowMapper<>(User.class))
//                .stream().findAny().orElse(null);
        TypedQuery<User> user = entityManager.createQuery(
                "select user from User user WHERE user.id=:id", User.class
        );
        return user.setParameter("id", id).getResultList().stream().findAny().orElse(null);
    }
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
//        jdbcTemplate.update("INSERT INTO USERS VALUES(?,?,?,?,?)", user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getAge());
    }
    @Transactional
    public void update(int id, User updatedUser) {
//        jdbcTemplate.update("UPDATE users set name=?,last_name=?,email=?,age=? where id=?", updatedUser.getName(),
//                updatedUser.getLastName(),updatedUser.getEmail(),updatedUser.getAge(), id);
        entityManager.merge(updatedUser);

    }
    @Transactional
    public void delete(int id) {
//        jdbcTemplate.update("Delete from users where id=?",id);
            entityManager.remove(show(id));
    }

}
