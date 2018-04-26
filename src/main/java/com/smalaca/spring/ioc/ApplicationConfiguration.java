package com.smalaca.spring.ioc;

import com.smalaca.spring.ioc.domain.BeanRepository;
import com.smalaca.spring.ioc.persistance.inmemory.InMemoryBeanRepositoryFactory;
import com.smalaca.spring.ioc.service.BeansService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public BeansService beansService() {
        return new BeansService(aBeanRepository());
    }

    private BeanRepository aBeanRepository() {
        return new InMemoryBeanRepositoryFactory().create();
    }
}