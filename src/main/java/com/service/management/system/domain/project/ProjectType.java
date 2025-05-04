package com.service.management.system.domain.project;

public enum ProjectType {
    NOT_STARTED(0),
    IN_PROGRESS(1),
    COMPLETED(2);
    private final int code;
    ProjectType(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public static ProjectType fromCode(int code) {
        for (ProjectType type : ProjectType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalStateException("Invalid ProjectType Code: " + code);
    }
}
