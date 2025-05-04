package com.service.management.system.dto.project;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ProjectWriteDto {
    private List<Integer> userFks;
    private List<MultipartFile> files;
    private int areaFk;
    private String contents;
    private LocalDate expectedDate;
    private LocalDate completeDate;
}
