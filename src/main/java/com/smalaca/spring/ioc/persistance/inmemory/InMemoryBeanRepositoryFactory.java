package com.smalaca.spring.ioc.persistance.inmemory;

import com.smalaca.spring.ioc.domain.BeanRepository;

public class InMemoryBeanRepositoryFactory {
    public BeanRepository create() {
        return new InMemoryBeanRepository(new UniqueIdentifierFactory());
    }
}
