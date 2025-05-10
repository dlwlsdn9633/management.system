package com.service.management.system.controller.mvc;

import com.service.management.system.domain.area.Area;
import com.service.management.system.domain.comment.Comment;
import com.service.management.system.domain.project.Project;
import com.service.management.system.dto.area.AreaReadDto;
import com.service.management.system.dto.project.ProjectWriteDto;
import com.service.management.system.service.AreaService;
import com.service.management.system.service.CommentService;
import com.service.management.system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final CommentService commentService;
    @GetMapping("/write")
    public String writeForm(Model model) {
        return "pages/projects/write";
    }
    @GetMapping("/{projectNo}")
    public String readForm(@PathVariable("projectNo") int no, Model model) {
        Project param = Project.builder().build();
        param.setNo(no);
        model.addAttribute("project", projectService.read(param));
        Comment commentParam = Comment.builder().build();
        commentParam.setProjectFk(no);
        model.addAttribute("comments", commentService.list(commentParam));
        return "pages/projects/read";
    }
    @GetMapping("/chunk")
    public String chunkForm() {
        return "pages/projects/chunk";
    }
    @PostMapping("/write")
    public String createProject(@ModelAttribute ProjectWriteDto projectWriteDto) {
        Project project = projectService.write(projectWriteDto);
        if (project == null) {
            return "redirect:/projects/write?error";
        }
        return "redirect:/projects/" + project.getNo();
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
