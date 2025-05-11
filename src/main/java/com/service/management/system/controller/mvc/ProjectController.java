package com.service.management.system.controller.mvc;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.domain.project.Project;
import com.service.management.system.dto.comment.CommentRequestDto;
import com.service.management.system.dto.project.ProjectRequestDto;
import com.service.management.system.service.CommentService;
import com.service.management.system.service.ProjectService;
import com.service.management.system.util.Function;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final CommentService commentService;
    private static final String WRITE_VIEW_PATH = "pages/projects/write";
    private static final String READ_VIEW_PATH = "pages/projects/read";
    private static final String CHUNK_VIEW_PATH = "pages/projects/chunk";
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("projectRequestDto", ProjectRequestDto.builder().build());
        return WRITE_VIEW_PATH;
    }
    @GetMapping("/{projectNo}")
    public String readForm(@PathVariable("projectNo") int no, Model model) {
        Project projectParam = Project.builder().no(no).build();
        Project readProject = projectService.read(projectParam);
        model.addAttribute("project", readProject);
        if (!"".equals(Function.isNull(readProject.getStoreFilenames()))) {
            List<String> fileList = Arrays.asList(readProject.getStoreFilenames().split(","));
            model.addAttribute("files", fileList);
        }

        Comment commentParam = Comment.builder().projectFk(no).build();
        model.addAttribute("comments", commentService.list(commentParam));
        return READ_VIEW_PATH;
    }
    @GetMapping("/chunk")
    public String chunkForm() {
        return CHUNK_VIEW_PATH;
    }
    @PostMapping("/write")
    public String createProject(
            @ModelAttribute @Valid ProjectRequestDto projectRequestDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            // 에러가 있다면, 다시 작성 폼으로, 모델에 따라 에러 정보를 포함한다.
            model.addAttribute("errors", bindingResult.getAllErrors());
            return WRITE_VIEW_PATH;
        }
        Project createdProject = projectService.write(projectRequestDto);
        if (createdProject == null) {
            model.addAttribute("error", "프로젝트 생성에 실패했습니다.");
            return WRITE_VIEW_PATH;
        }
        return "redirect:/projects/" + createdProject.getNo();
    }
    @PostMapping("/chunk")
    public String uploadExcelFile(@RequestParam("excel") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/project/chunk?error";
        }
        try {
            projectService.processExcelFile(file);
            return "redirect:/projects/chunk";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/project/chunk?error";
        }
    }
}
