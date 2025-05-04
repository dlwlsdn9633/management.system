package com.service.management.system.util;

import com.service.management.system.domain.fileObject.FileObject;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *  => 개선사항
 *  회사에서는 저장된 file을 attacheFile에 저장해두고 있음
 *  file.dir로 가져오는 방법이 올바른 방법일까?
 * */
@Slf4j
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename) {
        return Paths.get(fileDir, filename).toString();
    }
    // MultipartFiles?
    public List<FileObject> storeFiles(List<MultipartFile> multipartFiles) {
        List<FileObject> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                try {
                    FileObject fileObject = storeFile(multipartFile);
                    if (fileObject != null) {
                        files.add(fileObject);
                    }
                } catch (IOException e) {
                    log.error("Fail To Store Files: {}. Error: {}", multipartFile.getOriginalFilename(), e.getMessage());
                }
            }
        }
        return files;
    }
    public FileObject storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty() || "".equals(Function.isNull(multipartFile.getOriginalFilename()))) {
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFilename(originalFileName);
        log.info("originalFileName: {}, storeFileName: {}", originalFileName, storeFileName);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return FileObject.builder()
                .originalFilename(originalFileName)
                .storeFilename(storeFileName)
                .registerDate(LocalDateTime.now())
                .build();
    }

    private String createStoreFilename(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
