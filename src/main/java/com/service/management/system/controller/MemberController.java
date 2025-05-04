package com.service.management.system.controller;

import com.service.management.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

}
