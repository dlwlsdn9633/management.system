package com.service.management.system.domain.projectArea;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectArea {
    private int no;
    private int projectFk;
    private int areaFk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    private String[] orderByString;
}
