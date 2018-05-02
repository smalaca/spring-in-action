package com.smalaca.spring.ioc.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlreadyExistingBeanExceptionTest {
    private static final String SOME_NAME = "No name";

    @Test
    public void shouldCreateExceptionWithMessage() {
        AlreadyExistingBeanException exception = new AlreadyExistingBeanException(SOME_NAME);

        assertThat(exception.getMessage()).isEqualTo("Bean with given name: '" + SOME_NAME + "', already exists");
    }
}