package com.smalaca.spring.ioc.service;

public class NotExistingBeanException extends RuntimeException {
    NotExistingBeanException(String id) {
        super("There is no bean with given id: " + id);
    }
}
