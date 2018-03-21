package com.smalaca.spring.componenttests.ioc.xmlbased.service;

import com.smalaca.spring.ioc.xmlbased.service.BeansService;
import com.smalaca.spring.ioc.xmlbased.service.NotExistingBeanException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class BeansServiceTest {
    private static final String SOME_ID = "13";

    @Autowired private BeansService beansService;

    @Test
    public void shouldThrowExceptionIfBeanDoesNotExist() {
        Throwable exception = catchThrowable(() -> beansService.getBeanDto(SOME_ID));

        assertThat(exception)
                .isInstanceOf(NotExistingBeanException.class)
                .hasMessage("There is no bean with given id: " + SOME_ID);
    }
}
