package com.example.repository;

import com.example.model.User;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class UserRepositoryTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_create() {
        User user = createUser();
        userRepository.save(user);
    }

    @Test
    public void test_find_by_name() {
        User user = createUser();
        userRepository.save(user);
        Optional<User> userOptional = userRepository.findByName("hehe");
        assertThat(userOptional.isPresent(), is(true));
    }

    @Test
    public void test_update() {
        Optional<User> userOptional = userRepository.findByName("hehe");
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName("hehe2");
            userRepository.save(user);
        }
    }

    @Test
    public void test_delete() {
        Optional<User> userOptional = userRepository.findByName("hehe");
        userOptional.ifPresent(user -> userRepository.delete(user));
    }

    @Test
    public void test_findByNameAndPassword() {
        try {
            userRepository.save(createUser());
        } catch (Exception e) {
            // can throw exception
        }
        Optional<User> userOptional = userRepository.findByNameAndPassword("hehe", "hehe2");
        assertThat(userOptional.isPresent(), is(true));
        userOptional = userRepository.findByNameAndPassword2("hehe", "hehe2");
        assertThat(userOptional.isPresent(), is(true));
        userOptional = userRepository.findByNameAndPassword3("hehe", "hehe2");
        assertThat(userOptional.isPresent(), is(true));
    }

    @Test
    public void test_execute_sql_count() {
        try {
            userRepository.save(createUser());
        } catch (Exception e) {
            // can throw exception
        }
        Optional<User> userOptional = userRepository.findByName("hehe");
        User user = userOptional.get();
        userRepository.getOne(user.getId());
        userRepository.getOne(user.getId());
    }

    private User createUser() {
        User user = new User();
        user.setName("hehe");
        user.setPassword("hehe2");
        return user;
    }
}