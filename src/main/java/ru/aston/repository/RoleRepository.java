package ru.aston.repository;

import org.hibernate.SessionFactory;
import ru.aston.entity.Role;

import java.util.Optional;

public class RoleRepository extends BaseRepository<Role, Integer> {

    public RoleRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    public Optional<Role> findByName(String name) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Role r where r.name = :name", Role.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

}
