package com.service.management.system.domain;
public enum Role {
    ADMIN(0),
    NORMAL(1);
    private final int code;
    Role(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public static Role fromCode(int code) {
        for (Role role : Role.values()) {
            if (role.code == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role Code: " + code);
    }
}
