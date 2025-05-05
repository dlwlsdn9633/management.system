package com.service.management.system.controller.mvc;

import com.service.management.system.domain.area.Area;
import com.service.management.system.domain.project.Project;
import com.service.management.system.dto.area.AreaReadDto;
import com.service.management.system.dto.project.ProjectWriteDto;
import com.service.management.system.service.AreaService;
import com.service.management.system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final AreaService areaService;
    @GetMapping("/write")
    public String writeForm(Model model) {
        return "pages/projects/write";
    }
    @PostMapping("/write")
    public String createProject(@ModelAttribute ProjectWriteDto projectWriteDto) {
        Project project = projectService.write(projectWriteDto);
        if (project == null) {
            return "redirect:/projects/write?error";
        }
        return "redirect:/projects/" + project.getNo();
    }
}
