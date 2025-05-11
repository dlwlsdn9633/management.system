package com.service.management.system.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String tablename;
    private int projectFk;
    private int memberFk;
    private int groupNo;
    private int step;
    private int depth;
    private List<MultipartFile> files;
    @NotBlank(message = "내용을 입력해 주세요")
    private String contents;
}
