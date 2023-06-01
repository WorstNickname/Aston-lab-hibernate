package ru.aston;

import org.junit.jupiter.api.Test;
import ru.aston.entity.Department;
import ru.aston.entity.Role;
import ru.aston.entity.User;
import ru.aston.repository.UserRepository;
import ru.aston.util.HibernateUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    private static UserRepository userRepository;

    @Test
    void save_successful() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.getCurrentSession()) {
            var transaction = session.beginTransaction();

            var userRepository = new UserRepository(sessionFactory);

            var user = User.builder()
                    .email("test12345@test.com")
                    .password("123")
                    .build();
            user.addRole(Role.builder().name("test").build());
            user.setDepartment(Department.builder().name("dev").build());

            var savedUser = userRepository.save(user);
            assertThat(savedUser.getId()).isNotNull();

            transaction.commit();
        }
    }

}
