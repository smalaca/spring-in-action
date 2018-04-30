package com.smalaca.spring.componenttests.ioc.service;

import com.smalaca.spring.ioc.ApplicationConfiguration;
import com.smalaca.spring.ioc.domain.BeanDto;
import com.smalaca.spring.ioc.service.AlreadyExistingBeanException;
import com.smalaca.spring.ioc.service.BeansService;
import com.smalaca.spring.ioc.service.NotExistingBeanException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static com.smalaca.spring.ioc.domain.BeanDto.Builder.aBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BeansServiceComponentTest {
    @Qualifier("beansServiceWithSingletonMaster")
    @Autowired private BeansService beansService;

    @Test
    public void shouldThrowExceptionIfBeanDoesNotExist() {
        String id = randomId();

        Throwable exception = catchThrowable(() -> beansService.getBeanDto(id));

        assertThat(exception)
                .isInstanceOf(NotExistingBeanException.class)
                .hasMessage("There is no bean with given id: " + id);
    }

    private String randomId() {
        return randomString();
    }

    @Test
    public void shouldCreateNewBean() {
        BeanDto bean = aBean(randomName(), randomDescription()).build();

        BeanDto created = beansService.createNew(bean);

        assertBeansAreTheSame(created, beansService.getBeanDto(created.id()));
    }

    @Test
    public void shouldCreateNewBeanWhenNameIsDifferent() {
        String description = randomDescription();
        beansService.createNew(aBean(randomName(), description).build());
        BeanDto bean = aBean(randomName(), description).build();

        BeanDto created = beansService.createNew(bean);

        assertBeansAreTheSame(created, beansService.getBeanDto(created.id()));
    }

    private void assertBeansAreTheSame(BeanDto created, BeanDto found) {
        assertThat(created.id()).isEqualTo(found.id());
        assertThat(created.name()).isEqualTo(found.name());
        assertThat(created.description()).isEqualTo(found.description());
    }

    @Test
    public void shouldThrowExceptionWhenCreatesBeanWithTheSameName() {
        String name = randomName();
        beansService.createNew(aBean(name, randomDescription()).build());
        BeanDto bean = aBean(name, randomDescription()).build();

        Throwable exception = catchThrowable(() -> beansService.createNew(bean));

        assertThat(exception)
                .isInstanceOf(AlreadyExistingBeanException.class)
                .hasMessage("Bean with given name: '" + name + "', already exists");
    }

    @Test
    public void shouldThrowExceptionWhenDeletingNotExistBean() {
        String id = randomId();

        Throwable exception = catchThrowable(() -> beansService.remove(id));

        assertThat(exception)
                .isInstanceOf(NotExistingBeanException.class)
                .hasMessage("There is no bean with given id: " + id);
    }

    @Test
    public void shouldRemoveBean() {
        BeanDto persisted = beansService.createNew(aBean(randomName(), randomDescription()).build());

        beansService.remove(persisted.id());

        assertThat(catchThrowable(() -> beansService.getBeanDto(persisted.id())))
                .isInstanceOf(NotExistingBeanException.class)
                .hasMessage("There is no bean with given id: " + persisted.id());
    }

    private String randomDescription() {
        return randomString();
    }

    private String randomName() {
        return randomString();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
