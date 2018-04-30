package com.smalaca.spring.componenttests.ioc.service;

import com.smalaca.spring.ioc.ApplicationConfiguration;
import com.smalaca.spring.ioc.domain.BeanDto;
import com.smalaca.spring.ioc.service.AlreadyExistingBeanException;
import com.smalaca.spring.ioc.service.BeansService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BeanServicesScopeTests {
    @Autowired BeansService beansServiceWithSingletonMaster;
    @Autowired BeansService beansServiceWithSingletonSlave;
    @Autowired BeansService beansServiceWithPrototypePrimary;
    @Autowired BeansService beansServiceWithPrototypeBcp;

    @Test
    public void shouldNotBeAbleToCreateTheSameBeanInMasterAndSlaveServices() {
        String name = randomName();
        BeanDto beanDto = BeanDto.Builder.aBean(name, randomDescription()).build();
        beansServiceWithSingletonMaster.createNew(beanDto);

        Throwable exception = catchThrowable(() -> beansServiceWithSingletonSlave.createNew(beanDto));

        assertThat(exception)
                .isInstanceOf(AlreadyExistingBeanException.class)
                .hasMessage("Bean with given name: '" + name + "', already exists");
    }

    @Test
    public void shouldBeAbleToCreateTheSameBeanInPrimaryAndBcp() {
        BeanDto beanDto = BeanDto.Builder.aBean(randomName(), randomDescription()).build();

        BeanDto persistedOnPrimary = beansServiceWithPrototypePrimary.createNew(beanDto);
        BeanDto persistedOnBcp = beansServiceWithPrototypeBcp.createNew(beanDto);

        assertThat(persistedOnPrimary.id()).isNotEmpty();
        assertThat(persistedOnBcp.id()).isNotEmpty();
        assertThat(persistedOnPrimary.id()).isNotEqualTo(persistedOnBcp.id());
        assertThat(persistedOnPrimary.name()).isEqualTo(persistedOnBcp.name());
        assertThat(persistedOnPrimary.description()).isEqualTo(persistedOnBcp.description());
    }

    private String randomName() {
        return randomString();
    }

    private String randomDescription() {
        return randomString();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
