package com.service.management.system.repository.project;

import com.service.management.system.domain.project.Project;
import com.service.management.system.domain.projectArea.ProjectArea;
import com.service.management.system.domain.projectMember.ProjectMember;
import com.service.management.system.mapper.ProjectAreaMapper;
import com.service.management.system.mapper.ProjectMapper;
import com.service.management.system.mapper.ProjectMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisProjectRepositoryImpl implements ProjectRepository {
    private final ProjectMapper projectMapper;
    private final ProjectAreaMapper projectAreaMapper;
    private final ProjectMemberMapper projectMemberMapper;
    @Override
    public int insert(Project project) {
        return projectMapper.insert(project);
    }
    @Override
    public Project read(Project project) {
        return projectMapper.read(project);
    }
    @Override
    public List<Project> list(Project project) {
        return projectMapper.list(project);
    }

    @Override
    public List<ProjectMember> listByMemberFk(ProjectMember projectMember) {
        return projectMemberMapper.list(projectMember);

    }
    public List<ProjectMember> listRemainingMembers(ProjectMember projectMember) {
        return projectMemberMapper.listRemainingMembers(projectMember);
    }

    @Override
    public int insertProjectArea(ProjectArea projectArea) {
        return projectAreaMapper.insert(projectArea);
    }
    @Override
    public int insertProjectMember(ProjectMember projectMember) {
        return projectMemberMapper.insert(projectMember);
    }
}
