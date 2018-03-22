package com.smalaca.spring.ioc.xmlbased.service;

import com.smalaca.spring.ioc.xmlbased.domain.Bean;
import com.smalaca.spring.ioc.xmlbased.domain.BeanDto;
import com.smalaca.spring.ioc.xmlbased.domain.BeanRepository;

import static com.smalaca.spring.ioc.xmlbased.domain.Bean.Builder.aBean;

public class BeansService {
    private final BeanRepository beanRepository;

    BeansService(BeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    public BeanDto getBeanDto(String id) {
        if (beanRepository.existWithId(id)) {
            return beanRepository.getById(id).asDto();
        }

        throw new NotExistingBeanException(id);
    }

    public BeanDto createNew(BeanDto beanDto) {
        if (beanRepository.existWithName(beanDto.name())) {
            throw new AlreadyExistingBeanException(beanDto.name());
        }

        Bean bean = aBean(beanDto).build();
        return beanRepository.persist(bean).asDto();
    }

    public void remove(String id) {
        if (beanRepository.existWithId(id)) {
            beanRepository.remove(id);
        } else {
            throw new NotExistingBeanException(id);
        }
    }
}
