package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User transactionRollBack(Integer userId, String updateUserName) {
        User user = new User();
        user.setName(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        userRepository.save(user);

        User one = userRepository.getOne(userId);
        one.setName(updateUserName);
        return one;
    }
}
