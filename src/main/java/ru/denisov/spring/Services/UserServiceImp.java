package ru.denisov.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denisov.spring.DAO.UserDaoInt;
import ru.denisov.spring.models.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDaoInt userDaoInt;

    @Override
    public List<User> index() {
        return userDaoInt.index();
    }

    @Override
    public User show(int id) {
        return userDaoInt.show(id);
    }

    @Override
    public void save(User user) {
        userDaoInt.save(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        userDaoInt.update(id,updatedUser);
    }

    @Override
    public void delete(int id) {
        userDaoInt.delete(id);
    }
}
