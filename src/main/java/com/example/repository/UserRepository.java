package com.example.repository;

import com.example.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    // password 和 name 的位置专门写反用来测试Query 注解的， findByNameAndPassword可以直接执行的
    @Query("from User where password = ?2 and name = ?1")
    Optional<User> findByNameAndPassword(String name, String password);
}
