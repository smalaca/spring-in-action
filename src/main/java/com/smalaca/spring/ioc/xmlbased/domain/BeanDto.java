package com.smalaca.spring.ioc.xmlbased.domain;

import static com.smalaca.spring.ioc.xmlbased.domain.BeanDto.Builder.aBean;

public class BeanDto {
    private static final String NO_ID = "";

    private final String name;
    private final String description;
    private final String id;

    private BeanDto(Builder builder) {
        name = builder.name;
        description = builder.description;
        id = builder.id;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public boolean hasId() {
        return !NO_ID.equals(id());
    }

    public BeanDto withId(String id) {
        return aBean(name(), description()).withId(id).build();
    }

    public static class Builder {
        private final String name;
        private final String description;
        private String id;

        private Builder(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public static Builder aBean(String name, String description) {
            return new Builder(name, description).withId(NO_ID);
        }

        Builder withId(String id) {
            this.id = id;
            return this;
        }

        public BeanDto build() {
            return new BeanDto(this);
        }
    }
}
