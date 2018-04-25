package com.smalaca.spring.ioc.service;

import com.smalaca.spring.ioc.domain.dto.BeanDto;

public class BeansService {
    public BeanDto getBeanDto(String id) {
        throw new NotExistingBeanException(id);
    }
}
