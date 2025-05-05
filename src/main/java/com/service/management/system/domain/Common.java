package com.service.management.system.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Common {
    private int no;
    private String name;
    private Role role;
    private String tablename;
    private int startPageRows;
    private int limitPages;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
}
