package com.smalaca.spring.ioc.xmlbased.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotExistingBeanExceptionTest {
    private static final String SOME_ID = "13";

    @Test
    public void shouldCreateExceptionWithMessage() {
        NotExistingBeanException exception = new NotExistingBeanException(SOME_ID);

        assertThat(exception.getMessage()).isEqualTo("There is no bean with given id: " + SOME_ID);
    }
}