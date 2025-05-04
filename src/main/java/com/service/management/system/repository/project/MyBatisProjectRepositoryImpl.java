package com.service.management.system.repository.project;

import com.service.management.system.domain.project.Project;
import com.service.management.system.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisProjectRepositoryImpl implements ProjectRepository {
    private final ProjectMapper projectMapper;
    @Override
    public int insert(Project project) {
        return projectMapper.insert(project);
    }
}
