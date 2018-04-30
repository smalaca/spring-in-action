package com.smalaca.spring.ioc;

import com.smalaca.spring.ioc.domain.BeanRepository;
import com.smalaca.spring.ioc.persistance.inmemory.InMemoryBeanRepositoryFactory;
import com.smalaca.spring.ioc.service.BeansService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public BeansService beansServiceWithSingletonMaster() {
        return new BeansService(aBeanRepository());
    }

    @Bean
    public BeansService beansServiceWithSingletonSlave() {
        return new BeansService(aBeanRepository());
    }

    @Bean
    BeanRepository aBeanRepository() {
        return new InMemoryBeanRepositoryFactory().create();
    }

    @Bean
    public BeansService beansServiceWithPrototypePrimary() {
        return new BeansService(aBeanRepositoryPrototype());
    }

    @Bean
    public BeansService beansServiceWithPrototypeBcp() {
        return new BeansService(aBeanRepositoryPrototype());
    }

    @Bean
    @Scope("prototype")
    BeanRepository aBeanRepositoryPrototype() {
        return new InMemoryBeanRepositoryFactory().create();
    }
}
