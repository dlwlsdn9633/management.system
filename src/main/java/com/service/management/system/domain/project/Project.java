package com.service.management.system.domain.project;

import com.service.management.system.domain.enums.ProjectType;
import com.service.management.system.domain.member.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private int no;
    private int areaFk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDate;
    private ProjectType projectType;
    private String contents;

    private String area;
    private String memberFks;
    private String storeFilenames;
    private List<Member> memberList;
    private int commentMaxGno;
    private int memberFk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    private String[] orderByString;
}
