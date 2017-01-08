package com.example.repository.impl;

import com.example.model.User;
import java.util.List;
import javax.persistence.Query;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> {

    public List testImpl(String name) {
        Query query = em.createQuery("from User u where u.name like :name");
        return query.setParameter("name", name).getResultList();
    }
}
