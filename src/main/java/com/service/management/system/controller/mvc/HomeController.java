package com.service.management.system.controller.mvc;

import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.enums.MemberType;
import com.service.management.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemberService memberService;
    @GetMapping("/")
    public String home(
            Model model,
            @RequestParam(
                    value = "code",
                    required = false,
                    defaultValue = "1"
            ) Integer code
    ) {
        try {
            MemberType sMemberType = MemberType.fromCode(code);
            if (MemberType.DEFAULT == sMemberType) {
                throw new IllegalArgumentException();
            }
            Member member = Member.builder()
                    .memberType(sMemberType)
                    .build();
            model.addAttribute("members", memberService.list(member));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            model.addAttribute("members", List.of());
        }
        return "pages/index";
    }
    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }
    @GetMapping("/register")
    public String register() {
        return "pages/register";
    }
}
