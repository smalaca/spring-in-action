package com.smalaca.spring.ioc.xmlbased.service;

import com.smalaca.spring.ioc.xmlbased.domain.dto.BeanDto;

class BeansService {
    BeanDto getBeanDto(String id) {
        throw new NotExistingBean(id);
    }
}
