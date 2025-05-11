package com.service.management.system.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    private int no;
    private List<Integer> memberFks;
    private List<MultipartFile> files;
    private int areaFk;
    @NotBlank(message = "내용을 입력해주세요")
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completeDate;
}
