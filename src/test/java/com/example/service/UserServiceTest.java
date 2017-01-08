package com.example.service;

import com.example.IntegrationTest;
import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.UUID;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class UserServiceTest extends IntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_transaction_commit() {
        String name = UUID.randomUUID().toString();
        User user = userService.transactionRollBack(name);
        assertThat(user.getName(), is(name));
    }

    @Test
    public void test_transaction_rollback() {
        userRepository.save(createUser());
        try {
            User user = userService.transactionRollBack("hehe");
            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void test_page() {
        userRepository.save(createUser());
        Page<User> page = userService.page(1, 1);
        assertThat(page.isFirst(), is(true));
        assertThat(page.getContent().size(), is(1));
    }

    private User createUser() {
        User user = new User();
        user.setName("hehe");
        user.setPassword("hehe2");
        return user;
    }
}