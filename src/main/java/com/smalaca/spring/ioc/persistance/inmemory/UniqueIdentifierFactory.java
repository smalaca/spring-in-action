package com.smalaca.spring.ioc.persistance.inmemory;

import java.util.UUID;

class UniqueIdentifierFactory {
    String anId() {
        return UUID.randomUUID().toString();
    }
}
