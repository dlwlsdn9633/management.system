package com.service.management.system.controller.mvc;

import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.member.MemberType;
import com.service.management.system.domain.project.Project;
import com.service.management.system.domain.projectMember.ProjectMember;
import com.service.management.system.service.MemberService;
import com.service.management.system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final ProjectService projectService;
    @GetMapping("/{no}")
    public String read(@PathVariable("no") Integer no, Model model) {
        Member param = Member.builder().build();
        param.setNo(no);
        Member readMember = memberService.read(param);
        model.addAttribute("member", readMember);
        ProjectMember projectMemberParam = ProjectMember.builder()
                .memberFk(no)
                .build();
        List<ProjectMember> projectList = projectService.listByMemberFk(projectMemberParam);
        model.addAttribute("projectList", projectList);
        return "pages/members/detail";
    }
    @GetMapping("/rank")
    public String rank(Model model) {
        Member param = Member.builder()
                .memberType(MemberType.DEPARTMENT)
                .build();
        List<Member> rankList = memberService.list(param);
        model.addAttribute("rankList", rankList);
        return "pages/members/rank";
    }
}
