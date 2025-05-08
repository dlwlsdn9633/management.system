package com.service.management.system.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private int no;
    private String name;
    private String majorTypeLabel;
    private String memberTypeLabel;
}
