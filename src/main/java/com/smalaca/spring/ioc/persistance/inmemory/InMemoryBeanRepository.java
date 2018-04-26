package com.smalaca.spring.ioc.persistance.inmemory;

import com.smalaca.spring.ioc.domain.Bean;
import com.smalaca.spring.ioc.domain.BeanRepository;

import java.util.HashMap;
import java.util.Map;

import static com.smalaca.spring.ioc.domain.Bean.Builder.aBean;

class InMemoryBeanRepository implements BeanRepository {
    private final Map<String, Bean> beans = new HashMap<>();
    private final UniqueIdentifierFactory uniqueIdentifierFactory;

    InMemoryBeanRepository(UniqueIdentifierFactory uniqueIdentifierFactory) {
        this.uniqueIdentifierFactory = uniqueIdentifierFactory;
    }

    @Override
    public boolean existWithId(String id) {
        return beans.containsKey(id);
    }

    @Override
    public boolean existWithName(String name) {
        return beans.values().stream().anyMatch(bean -> bean.hasNameEqualTo(name));
    }

    @Override
    public Bean getById(String id) {
        if (existWithId(id)) {
            return beans.get(id);
        }

        throw new NullPointerException("There is no value for given id: " + id + ".");
    }

    @Override
    public Bean persist(Bean bean) {
        String id = uniqueIdentifierFactory.anId();
        Bean beanWithId = aBean(bean.asDto().withId(id)).build();
        beans.put(id, beanWithId);

        return beanWithId;
    }

    @Override
    public void remove(String id) {
        if (existWithId(id)) {
            beans.remove(id);
        } else {
            throw new NullPointerException("There is no value for given id: " + id + ".");
        }
    }
}
