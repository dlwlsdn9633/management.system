package com.service.management.system.domain.projectMember;

import com.service.management.system.domain.enums.ProjectType;
import com.service.management.system.domain.enums.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    private int projectFk;
    private int memberFk;

    private String contents;
    private ProjectType projectType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDate;
    private int daysRemaining;

    private int no;
    private String name;
    private Role role;
    private String tablename;
    private int startPageRows;
    private int limitPages;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    private String[] orderByString;
}
