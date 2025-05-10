package com.service.management.system.mapper;

import com.service.management.system.domain.projectMember.ProjectMember;

import java.util.List;

public interface ProjectMemberMapper {
    int insert(ProjectMember projectMember);

    List<ProjectMember> list(ProjectMember projectMember);
    List<ProjectMember> listRemainingMembers(ProjectMember projectMember);
}
