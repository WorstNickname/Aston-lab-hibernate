package ru.aston.repository;

import org.hibernate.SessionFactory;
import ru.aston.entity.User;

import java.util.Optional;

public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    public Optional<User> findByEmail(String email) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery(
                "from User u where u.email = :email",
                User.class);
        query.setParameter("email", email);
        return Optional.ofNullable(query.uniqueResult());
    }

}
