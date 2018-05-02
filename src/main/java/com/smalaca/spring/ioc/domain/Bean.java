package com.smalaca.spring.ioc.domain;

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
        return BeanDto.Builder.aBean(name, description).withId(id).build();
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
            Builder builder = new Builder(beanDto.name(), beanDto.description());

            if (beanDto.hasId()) {
                builder.withId(beanDto.id());
            }

            return builder;
        }

        private void withId(String id) {
            this.id = id;
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