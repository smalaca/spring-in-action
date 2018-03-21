package com.smalaca.spring.ioc.xmlbased.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanTest {
    private static final String SOME_ID = "13";
    private static final String SOME_NAME = "Mr Bean";
    private static final String DIFFERENT_NAME = "Mrs Bean";
    private static final String SOME_DESCRIPTION = "Once upon a time there was a bean...";

    @Test
    public void shouldCreateBeanFromBeanDto() {
        Bean bean = Bean.Builder.aBean(givenBeanDto()).build();

        assertBean(bean);
    }

    @Test
    public void shouldCreateBeanFromAnotherBean() {
        Bean bean = Bean.Builder.aBean(givenBean()).build();

        assertBean(bean);
    }

    @Test
    public void shouldCreateBeanWithId() {
        Bean bean = Bean.Builder.aBean(givenBean()).withId(SOME_ID).build();

        assertThat(bean.asDto().id()).isEqualTo(SOME_ID);
    }

    private void assertBean(Bean bean) {
        BeanDto beanDto = bean.asDto();
        assertThat(beanDto.id()).isNull();
        assertThat(beanDto.name()).isEqualTo(SOME_NAME);
        assertThat(beanDto.description()).isEqualTo(SOME_DESCRIPTION);
    }

    @Test
    public void shouldRecognizeWhenHasTheSameName() {
        assertThat(givenBean().hasNameEqualTo(SOME_NAME)).isTrue();
    }

    @Test
    public void shouldRecognizeWhenDoesNotHaveTheSameName() {
        assertThat(givenBean().hasNameEqualTo(DIFFERENT_NAME)).isFalse();
    }

    private Bean givenBean() {
        return Bean.Builder.aBean(givenBeanDto()).build();
    }

    private BeanDto givenBeanDto() {
        return BeanDto.Builder.aBean(SOME_NAME, SOME_DESCRIPTION).build();
    }
}