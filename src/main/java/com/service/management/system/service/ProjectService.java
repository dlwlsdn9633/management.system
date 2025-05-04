package com.service.management.system.service;


import com.service.management.system.domain.fileObject.FileObject;
import com.service.management.system.domain.project.Project;
import com.service.management.system.domain.project.ProjectType;
import com.service.management.system.dto.project.ProjectWriteDto;
import com.service.management.system.mapper.FileObjectMapper;
import com.service.management.system.mapper.ProjectMapper;
import com.service.management.system.repository.fileObject.FileObjectRepository;
import com.service.management.system.repository.project.ProjectRepository;
import com.service.management.system.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final FileStore fileStore;
    private final ProjectRepository projectRepository;
    private final FileObjectRepository fileObjectRepository;
    public Project write(ProjectWriteDto projectWriteDto) {
        Project project = Project.builder()
                .contents(projectWriteDto.getContents())
                .expectedDate(projectWriteDto.getExpectedDate())
                .completeDate(projectWriteDto.getCompleteDate())
                .projectType(ProjectType.NOT_STARTED)
                .areaFk(projectWriteDto.getAreaFk())
                .build();

        int insertResult = projectRepository.insert(project);

        if (insertResult > 0) {
            if (!projectWriteDto.getFiles().isEmpty()) {
                List<FileObject> storedFiles = fileStore.storeFiles(projectWriteDto.getFiles());
                for (FileObject storedFile : storedFiles) {
                    storedFile.setTablename("projects");
                    storedFile.setCommonNo(project.getNo());
                    fileObjectRepository.insert(storedFile);
                }
            }
            return project;
        }
        return null;
    }
}
