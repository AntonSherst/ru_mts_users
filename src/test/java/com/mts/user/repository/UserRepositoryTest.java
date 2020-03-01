package com.mts.user.repository;

import com.mts.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс тест репозитория пользователя
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        entityManager.persist(new User(null, "Понедельнков"));
        entityManager.persist(new User(null, "Вторник"));
    }

    @Test
    public void testFindById() {
        User u = entityManager.persist(new User(null, "Именнуемый"));
        User user = repository.findById(u.getId()).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user).extracting(User::getName).isEqualTo("Именнуемый");
    }

    @Test
    public void testFindAll() {
        Iterable<User> users = repository.findAll();
        assertThat(users).isNotNull();
        assertThat(users).size().isGreaterThan(1);
    }
}