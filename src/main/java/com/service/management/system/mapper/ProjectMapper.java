package com.service.management.system.mapper;

import com.service.management.system.domain.project.Project;

import java.util.List;
public interface ProjectMapper {
    int insert(Project project);
    List<Project> list(Project project);
    Project read(Project project);
}
