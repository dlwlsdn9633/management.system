package com.service.management.system.domain.area;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private int no;
    private String contents;
    private int areaType;
    private int relNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
}
