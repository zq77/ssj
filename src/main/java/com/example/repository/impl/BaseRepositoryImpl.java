package com.example.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepositoryImpl<T> {

    @PersistenceContext
    protected EntityManager em;

    protected void deleteAndFlush(T obj) {
        em.remove(obj);
        em.flush();
    }
}
