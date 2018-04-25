package com.smalaca.spring.ioc.domain;

import org.junit.Test;

import static com.smalaca.spring.ioc.domain.BeanDto.Builder.aBean;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanDtoTest {
    private static final String NO_ID = "";
    private static final String SOME_ID = "69";
    private static final String SOME_NAME = "name";
    private static final String SOME_DESCRIPTION = "description";

    @Test
    public void shouldCreateBeanWithNoId() {
        BeanDto beanDto = aBean(SOME_NAME, SOME_DESCRIPTION).build();

        assertBeanDto(beanDto, NO_ID);
    }

    @Test
    public void shouldCreateBeanWithId() {
        BeanDto beanDto = aBean(SOME_NAME, SOME_DESCRIPTION).withId(SOME_ID).build();

        assertBeanDto(beanDto, SOME_ID);
    }

    private void assertBeanDto(BeanDto beanDto, String id) {
        assertThat(beanDto.id()).isEqualTo(id);
        assertThat(beanDto.name()).isEqualTo(SOME_NAME);
        assertThat(beanDto.description()).isEqualTo(SOME_DESCRIPTION);
    }
}