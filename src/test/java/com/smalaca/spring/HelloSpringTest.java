package com.smalaca.spring;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloSpringTest {
    private static final String SPRING_IN_ACTION = "Spring in action";

    @Test
    public void shouldSayHello() {
        Sentence sentence = new Sentence(SPRING_IN_ACTION);

        assertThat(sentence.say()).isEqualTo(SPRING_IN_ACTION);
    }
}
