package ru.denisov.spring.Services;

import ru.denisov.spring.DAO.UserDao;
import ru.denisov.spring.models.User;

import java.util.List;

public interface UserService extends UserDao {
    public List<User> index();
    public User show (int id);
    public void save(User user);
    public void update(int id, User updatedUser);
    public void delete(int id);
    public User getUserByName(String name);
}
