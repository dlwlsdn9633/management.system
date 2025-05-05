package com.service.management.system.domain.fileObject;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileObject {
    private int no;
    private String tablename;
    private int commonNo;
    private String originalFilename;
    private String storeFilename;
    private LocalDateTime registerDate;
}