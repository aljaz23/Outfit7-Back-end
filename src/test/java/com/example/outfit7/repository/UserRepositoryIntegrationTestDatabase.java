package com.example.outfit7.repository;

import com.example.outfit7.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTestDatabase {
    @Autowired
    UserRepository userRepository;

    @Test
    void Should_Create_User() {
        User user = new User();
        user.setTimeZone("Europe/Ljubljana");
        user.setUserId("test123");
        user.setCountryCode("SL");
        userRepository.save(user);
        assertNotNull(userRepository.getReferenceById("test123"));
    }

    @Test
    void Should_Get_All_Users() {
        Should_Create_User();
        List<User> userList = userRepository.findAll();
        assertThat(userList).size().isGreaterThan(0);
    }

    @Test
    void Should_Get_Single_User() {
        Should_Create_User();
        User user = userRepository.findById("test123").get();
        assertEquals("SL", user.getCountryCode());
    }

    @Test
    void Should_Delete_User() {
        Should_Create_User();
        userRepository.deleteById("test123");
        assertThat(userRepository.existsById("test123")).isFalse();
    }
}
