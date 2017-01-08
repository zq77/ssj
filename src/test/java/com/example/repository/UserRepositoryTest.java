package com.example.repository;

import com.example.IntegrationTest;
import com.example.model.User;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserRepositoryTest extends IntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        Optional<User> userOptional = userRepository.findByName("hehe");
        if (!userOptional.isPresent()) {
            userRepository.save(createUser());
        }
    }

    @Test
    public void test_find_by_name() {
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
        Optional<User> userOptional = userRepository.findByNameAndPassword("hehe", "hehe2");
        assertThat(userOptional.isPresent(), is(true));
        userOptional = userRepository.findByNameAndPassword2("hehe", "hehe2");
        assertThat(userOptional.isPresent(), is(true));
        userOptional = userRepository.findByNameAndPassword3("hehe", "hehe2");
        assertThat(userOptional.isPresent(), is(true));
    }

    @Test
    public void test_execute_sql_count() {
        Optional<User> userOptional = userRepository.findByName("hehe");
        User user = userOptional.get();
        userRepository.getOne(user.getId());
        userRepository.getOne(user.getId());
    }

    @Test
    public void test_impl() {
        List<User> users = userRepository.testImpl("hehe");
        assertThat(users.size(), is(1));
        users = userRepository.testImpl("hah0");
        assertThat(users.size(), is(0));
    }

    private User createUser() {
        User user = new User();
        user.setName("hehe");
        user.setPassword("hehe2");
        return user;
    }
}