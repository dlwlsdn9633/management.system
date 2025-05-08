package com.service.management.system.domain.member;

public enum MemberType {
    DEFAULT(0, "DEFAULT"),
    DEPARTMENT(1, "부서"),
    MAJOR(2, "학과");
    private final int code;
    private final String label;
    MemberType(int code, String label) {
        this.code = code;
        this.label = label;
    }
    public int getCode() {
        return code;
    }
    public String getLabel() {
        return label;
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
