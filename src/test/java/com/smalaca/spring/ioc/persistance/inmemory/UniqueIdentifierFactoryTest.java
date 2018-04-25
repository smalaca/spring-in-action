package com.smalaca.spring.ioc.persistance.inmemory;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueIdentifierFactoryTest {
    private final UniqueIdentifierFactory factory = new UniqueIdentifierFactory();

    @Test
    public void shouldCreateStringId() {
        String id = factory.anId();

        assertThat(id).isNotEmpty();
    }

    @Test
    public void shouldCreateDifferentStringIds() {
        assertThat(factory.anId()).isNotEqualTo(factory.anId());
    }
}