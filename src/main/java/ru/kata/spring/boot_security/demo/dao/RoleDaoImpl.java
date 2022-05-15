package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getListRoles() {
        return entityManager.createQuery("SELECT role FROM Role role", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("SELECT role FROM Role role WHERE role.name =:name", Role.class).setParameter("name", name).getSingleResult();
    }
}