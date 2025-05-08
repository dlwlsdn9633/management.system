package com.service.management.system.service;


import com.service.management.system.domain.fileObject.FileObject;
import com.service.management.system.repository.fileObject.FileObjectRepository;
import com.service.management.system.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FileObjectService {
    private final FileStore fileStore;
    private final FileObjectRepository fileObjectRepository;
    public int insertFiles(List<MultipartFile> files, int commonNo, String tablename) {
        int insertedFilesNumber = 0;
        if (!files.isEmpty()) {
            List<FileObject> storedFiles = fileStore.storeFiles(files);
            for (FileObject storedFile : storedFiles) {
                storedFile.setTablename(tablename);
                storedFile.setCommonNo(commonNo);
                fileObjectRepository.insert(storedFile);
                ++insertedFilesNumber;
            }
        }
        return insertedFilesNumber;
    }
}
