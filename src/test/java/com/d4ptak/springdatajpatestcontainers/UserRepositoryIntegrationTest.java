package com.d4ptak.springdatajpatestcontainers;

import com.d4ptak.springdatajpatestcontainers.user.User;
import com.d4ptak.springdatajpatestcontainers.user.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest
@ContextConfiguration(initializers = { AbstractIntegrationTest.Initializer.class })
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void shouldContextLoadTest() {
        assertThat(userJpaRepository).isNotNull();
    }

    @Test
    void shouldContainerRunTest() {
        assertThat(POSTGRESQL_CONTAINER.isRunning()).isTrue();
    }

    @Test
    void shouldUserAddTest() {
        final User test = User.create("test");
        userJpaRepository.save(test);

        final List<User> users = userJpaRepository.findAll();
        assertThat(users.isEmpty()).isFalse();
    }
}
