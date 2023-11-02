package com.sparta.lv3.entity;

import lombok.Getter;

@Getter
public enum Division {
    DEVELOPMENT(Authority.MANAGER),
    CURRICULUM(Authority.MANAGER),
    MARKETING(Authority.STAFF);

    private final String authority;

    Division(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}
