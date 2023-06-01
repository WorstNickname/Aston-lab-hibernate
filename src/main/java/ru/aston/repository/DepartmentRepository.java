package ru.aston.repository;

import org.hibernate.SessionFactory;
import ru.aston.entity.Department;

import java.util.Optional;

public class DepartmentRepository extends BaseRepository<Department, Integer> {

    public DepartmentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Department.class);
    }

    public Optional<Department> findByName(String name) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery(
                "from Department d where d.name = :name", Department.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

}
