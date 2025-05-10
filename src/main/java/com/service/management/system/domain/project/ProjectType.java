package com.service.management.system.domain.project;

public enum ProjectType {
    NOT_STARTED(0, "시작 전"),
    IN_PROGRESS(1, "진행 중"),
    COMPLETED(2, "완료");
    private final int code;
    private final String label;
    ProjectType(int code, String label) {
        this.code = code;
        this.label = label;
    }
    public int getCode() {
        return code;
    }
    public String getLabel() {
        return label;
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
