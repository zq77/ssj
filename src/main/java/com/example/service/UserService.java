package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.UUID;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Transactional
    public User transactionRollBack(String updateUserName) {
        User user = new User();
        user.setName(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        userRepository.save(user);

        User one = userRepository.getOne(user.getId());
        one.setName(updateUserName);
        return one;
    }

    public Page<User> page(int pageNumber,int pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, null);
        Page<User> page = userRepository.findAll(request);
        return page;
    }
}
