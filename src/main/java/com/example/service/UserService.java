package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<User> page(int pageNumber,int pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, null);
        Page<User> page = userRepository.findAll(request);
        return page;
    }
}
