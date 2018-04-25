package com.smalaca.spring.ioc.domain;

public interface BeanRepository {
    Bean persist(Bean bean);

    boolean existWithId(String id);

    boolean existWithName(String name);

    Bean getById(String id);
}
