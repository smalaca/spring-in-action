package com.smalaca.spring.ioc.xmlbased.service;

public class AlreadyExistingBeanException extends RuntimeException {

    AlreadyExistingBeanException(String name) {
        super("Bean with given name: '" + name + "', already exists");
    }
}
