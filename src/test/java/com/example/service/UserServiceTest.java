package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_transaction_commit() {
        String name = UUID.randomUUID().toString();
        User user = userService.transactionRollBack(1, name);
        assertThat(user.getName(), is(name));
    }

    @Test
    public void test_transaction_rollback() {
        try {
            userRepository.save(createUser());
        } catch (Exception e) {

        }
        try {
            User user = userService.transactionRollBack(1, "hehe");
            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void test_page() {
        Page<User> page = userService.page(1, 2);
        assertThat(page.isFirst(), is(true));
        assertThat(page.getContent().size(), is(2));
    }

    private User createUser() {
        User user = new User();
        user.setName("hehe");
        user.setPassword("hehe2");
        return user;
    }
}