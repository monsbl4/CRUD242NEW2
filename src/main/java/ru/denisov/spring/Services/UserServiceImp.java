package ru.denisov.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.denisov.spring.DAO.RoleDao;
import ru.denisov.spring.DAO.UserDao;
import ru.denisov.spring.models.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        userDao.update(id,updatedUser);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%username' not found!", username));
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), user.getAuthorities());
        return userDetails;
    }
}
