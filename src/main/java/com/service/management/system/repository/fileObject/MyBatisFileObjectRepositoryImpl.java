package com.service.management.system.repository.fileObject;

import com.service.management.system.domain.fileObject.FileObject;
import com.service.management.system.mapper.FileObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisFileObjectRepositoryImpl implements FileObjectRepository {
    private final FileObjectMapper fileObjectMapper;
    @Override
    public int insert(FileObject fileObject) {
        return fileObjectMapper.insert(fileObject);
    }
}
