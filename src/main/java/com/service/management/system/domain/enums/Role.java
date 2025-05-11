package com.service.management.system.domain.enums;
public enum Role {
    ADMIN(0, "관리자"),
    NORMAL(1, "일반");
    private final int code;
    private final String label;
    Role(int code, String label) {
        this.code = code;
        this.label = label;
    }
    public int getCode() {
        return code;
    }
    public String getLabel() {
        return this.label;
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
