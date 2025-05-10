package com.service.management.system.repository.project;

import com.service.management.system.domain.project.Project;
import com.service.management.system.domain.projectArea.ProjectArea;
import com.service.management.system.domain.projectMember.ProjectMember;

import java.util.List;

public interface ProjectRepository {
    int insert(Project project);
    Project read(Project project);
    List<Project> list(Project project);
    List<ProjectMember> listByMemberFk(ProjectMember projectMember);
    List<ProjectMember> listRemainingMembers(ProjectMember projectMember);
    int insertProjectArea(ProjectArea projectArea);
    int insertProjectMember(ProjectMember projectMember);

}
