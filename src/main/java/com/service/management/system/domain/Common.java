package com.service.management.system.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Common {
    private int no;
    private String name;
    private Role role;
    private LocalDate registerDate;
    private String tablename;
    private int startPageRows;
    private int limitPages;
}
