package com.smalaca.spring.ioc.xmlbased.service;

public class NotExistingBeanException extends RuntimeException {
    NotExistingBeanException(String id) {
        super("There is no bean with given id: " + id);
    }
}
