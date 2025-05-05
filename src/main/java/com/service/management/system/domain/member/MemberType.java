package com.service.management.system.domain.member;

public enum MemberType {
    DEFAULT(0),
    DEPARTMENT(1),
    MAJOR(2);
    private final int code;
    MemberType(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public static MemberType fromCode(int code) {
        for (MemberType type : MemberType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MemberType Code: " + code);
    }
}
