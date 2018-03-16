package com.smalaca.spring.ioc.xmlbased.service;

public class NotExistingBean extends RuntimeException {
    NotExistingBean(String id) {
        super("There is no bean with given id: " + id);
    }
}
