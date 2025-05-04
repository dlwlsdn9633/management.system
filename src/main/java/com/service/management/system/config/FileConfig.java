package com.service.management.system.config;

import com.service.management.system.util.FileStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

// 여기서 여러 파일들을 생성되게 만들면 될듯?
// /upload/projects, /upload/users ...

@Slf4j
@Configuration
public class FileConfig {
    @Value("${file.dir}")
    private String fileDir;
    @Bean
    public FileStore fileFunction() {
        File folder = new File(fileDir);
        if (!folder.exists()) {
            try {
                folder.mkdirs();
                log.info("Success To Create");
            } catch (Exception e) {
                log.error("error", e);
            }
        } else {
            log.info("Already Existed The Folder");
        }
        return new FileStore();
    }
}
