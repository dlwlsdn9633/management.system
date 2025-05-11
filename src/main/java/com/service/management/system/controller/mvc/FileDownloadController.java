package com.service.management.system.controller.mvc;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/download")
public class FileDownloadController {
    @Value("${file.dir}")
    private String fileStorageLocation;

    @GetMapping("/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        File file = new File(fileStorageLocation + "/" + filename);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }
        // 파일 다운로드 응답 헤더 설정
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachement; filename=\"" + file.getName() + "\"");

        // 파일을 스트리밍으로 출력
        try (FileInputStream inStream = new FileInputStream(file)) {
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
