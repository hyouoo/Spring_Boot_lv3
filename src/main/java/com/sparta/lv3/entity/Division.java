package com.sparta.lv3.entity;

public enum Division {
    DEVELOPMENT(Authority.MANAGER),
    CURICULLUM(Authority.MANAGER),
    MARKETTING(Authority.STAFF);

    private final String authority;

    Division(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}
