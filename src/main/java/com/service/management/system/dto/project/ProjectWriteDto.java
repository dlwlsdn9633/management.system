package com.service.management.system.dto.project;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ProjectWriteDto {
    private List<Integer> memberFks;
    private List<MultipartFile> files;
    private int areaFk;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completeDate;
}
