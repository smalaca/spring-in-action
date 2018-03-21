package com.smalaca.spring.ioc.xmlbased.service;

import com.smalaca.spring.ioc.xmlbased.domain.dto.BeanDto;

public class BeansService {
    public BeanDto getBeanDto(String id) {
        throw new NotExistingBeanException(id);
    }
}
