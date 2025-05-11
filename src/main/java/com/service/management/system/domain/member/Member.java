package com.service.management.system.domain.member;

import com.service.management.system.domain.enums.MajorType;
import com.service.management.system.domain.enums.MemberType;
import com.service.management.system.domain.enums.Role;
import com.service.management.system.dto.member.MemberResponseDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int no;
    private String name;
    private Role role;
    private String tablename;
    private int startPageRows;
    private int limitPages;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    private String[] orderByString;

    private String id;
    private String email;
    private String password;
    private MemberType memberType;
    private MajorType majorType;
    private int total;
    private int completeProjectsNum;
    private double ratio;

    public MemberResponseDto getMemberResponseDto() {
        return MemberResponseDto.builder()
                .no(this.no)
                .name(this.name)
                .majorTypeLabel(this.majorType.getLabel())
                .memberTypeLabel(this.memberType.getLabel())
                .build();
    }
}
