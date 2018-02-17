package com.smalaca.spring;

class Sentence {
    private final String sentence;

    Sentence(String sentence) {
        this.sentence = sentence;
    }

    String say() {
        return sentence;
    }
}
