package com.service.management.system.controller.fragments;

import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.enums.MemberType;
import com.service.management.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members/fragments")
public class MemberFragmentController {
    private final MemberService memberService;
    @GetMapping("/rank/{code}")
    public String getMemberRanks(@PathVariable("code") Integer code, Model model) {
        MemberType memberType = MemberType.fromCode(code);
        Member param = Member.builder()
                .memberType(memberType)
                .build();
        param.setOrderByString(new String[] { "ratio DESC "});
        List<Member> rankList = memberService.list(param);
        model.addAttribute("rankList", rankList);
        return "fragments/rankList :: rankListFragment";
    }
}
