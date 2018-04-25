package com.smalaca.spring.ioc.domain;

import static com.smalaca.spring.ioc.domain.BeanDto.Builder.aBean;

public class Bean {
    private final String name;
    private final String description;
    private String id;

    private Bean(Builder builder) {
        name = builder.name;
        description = builder.description;
    }

    private Bean(Builder builder, String id) {
        this(builder);
        this.id = id;
    }

    public BeanDto asDto() {
        return aBean(name, description).withId(id).build();
    }

    public boolean hasNameEqualTo(String name) {
        return this.name.equals(name);
    }

    public static class Builder {
        private final String name;
        private final String description;
        private String id;

        private Builder(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public static Builder aBean(BeanDto beanDto) {
            return new Builder(beanDto.name(), beanDto.description());
        }

        public static Builder aBean(Bean bean) {
            return new Builder(bean.name, bean.description);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Bean build() {
            if (id != null) {
                return new Bean(this, id);
            } else {
                return new Bean(this);
            }
        }
    }
}