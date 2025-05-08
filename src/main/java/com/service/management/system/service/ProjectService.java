package com.service.management.system.service;

import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.project.Project;
import com.service.management.system.domain.project.ProjectType;
import com.service.management.system.domain.projectArea.ProjectArea;
import com.service.management.system.domain.projectMember.ProjectMember;
import com.service.management.system.dto.project.ProjectWriteDto;
import com.service.management.system.repository.member.MemberRepository;
import com.service.management.system.repository.project.ProjectRepository;
import com.service.management.system.util.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private static final String TABLE_NAME = "projects";
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final FileObjectService fileObjectService;

    public List<Project> list(ProjectType projectType) {
        Project project = Project.builder()
                .projectType(projectType)
                .build();
        return projectRepository.list(project);
    }

    public List<ProjectMember> listByMemberFk(ProjectMember projectMember) {
        return projectRepository.listByMemberFk(projectMember);
    }


    public Project read(Project project) {
        Project readProject = projectRepository.read(project);
        setMemberList(readProject);
        return readProject;
    }
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
            // 관계 테이블 삽입
            insertProjectArea(project, projectWriteDto);
            insertProjectMember(project, projectWriteDto);
            fileObjectService.insertFiles(projectWriteDto.getFiles(), project.getNo(), TABLE_NAME);
            return project;
        }
        return null;
    }
    private void insertProjectArea(Project project, ProjectWriteDto projectWriteDto) {
        ProjectArea projectArea = ProjectArea.builder()
                .projectFk(project.getNo())
                .areaFk(projectWriteDto.getAreaFk())
                .build();
        projectRepository.insertProjectArea(projectArea);

    }
    private void insertProjectMember(Project project, ProjectWriteDto projectWriteDto) {
        log.info("{}", projectWriteDto);
        for (int memberFk : projectWriteDto.getMemberFks()) {
            ProjectMember projectMember = ProjectMember.builder()
                    .projectFk(project.getNo())
                    .memberFk(memberFk)
                    .build();
            projectRepository.insertProjectMember(projectMember);
        }
    }
    private void setMemberList(Project project) {
        if (!"".equals(Function.isNull(project.getMemberFks()))) {
            String[] memberFksArr = project.getMemberFks().split(",");
            List<Member> memberList = new ArrayList<>();
            for (String memberFk : memberFksArr) {
                Member memberParam = Member.builder().build();
                memberParam.setNo(Integer.parseInt(memberFk));
                memberList.add(memberRepository.read(memberParam));
            }
            project.setMemberList(memberList);
        }
    }
}
