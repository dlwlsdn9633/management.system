package com.service.management.system.domain.member;

import java.util.*;

public enum MajorType {
    DEFAULT(0, "-"),
    THEOLOGY_COLLEGE(1, "신학대학"),
    HUMANITIES_AND_SOCIAL_SCIENCES(2, "인문사회과학"),
    BUSINESS_COLLEGE(3, "경영대학"),
    SCIENCE_AND_ENGINEERING_COLLEGE(4, "이과대학"),
    ARTS_COLLEGE(5, "예술대학");
    private final int code;
    private final String label;
    MajorType(int code, String label) {
        this.code = code;
        this.label = label;
    }
    public int getCode() {
        return code;
    }
    public String getLabel() {
        return label;
    }

    public static MajorType fromCode(int code) {
        for (MajorType type : MajorType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MajorType Code: " + code);
    }
    public static List<Map<String, Object>> getLabels() {
        return Arrays.stream(MajorType.values())
                .filter(x -> x.code != 0)
                .map(x -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", x.code);
                    map.put("label", x.label);
                    return map;
                })
                .toList();
    }


}
