package ru.denisov.spring.DAO;

import org.springframework.stereotype.Repository;
import ru.denisov.spring.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleImpl implements RoleDao{
    @PersistenceContext
    EntityManager em;


    @Override
    public List<Role> getRoles() {

        return em.createQuery("SELECT role FROM Role role", Role.class).getResultList();
    }
}
