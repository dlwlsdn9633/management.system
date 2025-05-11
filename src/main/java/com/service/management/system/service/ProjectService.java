package com.service.management.system.service;

import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.project.Project;
import com.service.management.system.domain.enums.ProjectType;
import com.service.management.system.domain.projectArea.ProjectArea;
import com.service.management.system.domain.projectMember.ProjectMember;
import com.service.management.system.dto.project.ProjectRequestDto;
import com.service.management.system.repository.member.MemberRepository;
import com.service.management.system.repository.project.ProjectRepository;
import com.service.management.system.util.ExcelUtil;
import com.service.management.system.util.Function;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private static final String TABLE_NAME = "projects";
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final FileObjectService fileObjectService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    public void processExcelFile(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            // 첫 번째 줄은 제목이므로 건너뛰기
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            // 각 행을 별도의 Thread로 처리
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                executorService.submit(() -> processRow(row));
            }
        } catch (IOException e) {
            throw new IOException("파일 처리 중 오류 발생", e);
        }
    }

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
    private void processRow(Row row) {
        ProjectExcelRow projectExcelRow = new ProjectExcelRow(row);
        Project project = projectExcelRow.getProject();
        int insertResult = projectRepository.insert(project);
        projectExcelRow.projectFk = project.getNo();

        if (insertResult > 0) {
            ProjectMember projectMember = projectExcelRow.getProjectMember();
            ProjectArea projectArea = projectExcelRow.getProjectArea();
            projectRepository.insertProjectMember(projectMember);
            projectRepository.insertProjectArea(projectArea);
        }
    }
    public Project write(ProjectRequestDto projectRequestDto) {
        Project project = Project.builder()
                .contents(projectRequestDto.getContents())
                .requestDate(projectRequestDto.getRequestDate())
                .expectedDate(projectRequestDto.getExpectedDate())
                .projectType(ProjectType.NOT_STARTED)
                .areaFk(projectRequestDto.getAreaFk())
                .build();
        int insertResult = projectRepository.insert(project);
        if (insertResult > 0) {
            // 관계 테이블 삽입
            insertProjectArea(project, projectRequestDto);
            insertProjectMember(project, projectRequestDto);
            fileObjectService.insertFiles(projectRequestDto.getFiles(), project.getNo(), TABLE_NAME);
            return project;
        }
        return null;
    }
    private void insertProjectArea(Project project, ProjectRequestDto projectRequestDto) {
        ProjectArea projectArea = ProjectArea.builder()
                .projectFk(project.getNo())
                .areaFk(projectRequestDto.getAreaFk())
                .build();
        projectRepository.insertProjectArea(projectArea);

    }
    private void insertProjectMember(Project project, ProjectRequestDto projectRequestDto) {
        for (int memberFk : projectRequestDto.getMemberFks()) {
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
    public List<ProjectMember> listRemainingMembers(ProjectMember projectMember) {
        return projectRepository.listRemainingMembers(projectMember);
    }
    @PreDestroy
    public void shutdownExecutor() {
        if (!executorService.isShutdown()) {
            log.info("[+] executorService is shutdown");
            executorService.shutdown();
        }
    }
    static class ProjectExcelRow {
        private int projectFk;
        private int memberFk;
        private int areaFk;
        private String contents;
        private LocalDate requestDate;
        private LocalDate expectedDate;
        public ProjectExcelRow (Row row) {
            this.memberFk = ExcelUtil.getValue(row.getCell(0), Integer.class).orElse(0);
            this.areaFk = ExcelUtil.getValue(row.getCell(1), Integer.class).orElse(0);
            this.contents = ExcelUtil.getValue(row.getCell(2), String.class).orElse(Function.isNull(null));
            this.requestDate = ExcelUtil.getValue(row.getCell(3), LocalDate.class).orElse(LocalDate.now());
            this.expectedDate = ExcelUtil.getValue(row.getCell(4), LocalDate.class).orElse(LocalDate.now());
        }
        public Project getProject() {
            Project project = Project.builder()
                    .areaFk(this.areaFk)
                    .memberFk(this.memberFk)
                    .contents(this.contents)
                    .requestDate(this.requestDate)
                    .expectedDate(this.expectedDate)
                    .projectType(ProjectType.NOT_STARTED)
                    .build();
            log.info("project: {}", project);
            return project;
        }
        public ProjectMember getProjectMember() {
            ProjectMember projectMember = ProjectMember.builder()
                    .projectFk(projectFk)
                    .memberFk(memberFk)
                    .build();
            log.info("projectMember: {}", projectMember);
            return projectMember;
        }
        public ProjectArea getProjectArea() {
            ProjectArea projectArea = ProjectArea.builder()
                    .projectFk(projectFk)
                    .areaFk(areaFk)
                    .build();
            log.info("projectArea: {}", projectArea);
            return projectArea;
        }
    }
}
