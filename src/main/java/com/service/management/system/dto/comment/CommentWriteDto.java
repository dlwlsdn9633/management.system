package com.service.management.system.dto.comment;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CommentWriteDto {
    private String tablename;
    private int projectFk;
    private int memberFk;
    private int groupNo;
    private int step;
    private int depth;
    private List<MultipartFile> files;
    private String contents;
}
