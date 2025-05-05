package com.service.management.system.controller.api;

import com.service.management.system.domain.member.MajorType;
import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.member.MemberType;
import com.service.management.system.dto.ApiResponse;
import com.service.management.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberApiController {
    private final MemberService memberService;
    @GetMapping("")
    public ResponseEntity<?> list(
            @RequestParam(required = false, defaultValue = "0") Integer memberType,
            @RequestParam(required = false, defaultValue = "0") Integer majorType
    ) {
        Member member = Member.builder()
                .memberType(MemberType.fromCode(memberType))
                .majorType(MajorType.fromCode(majorType))
                .build();
        List<Member> list = memberService.list(member);
        return ResponseEntity.ok(ApiResponse.success(list));
    }
    @GetMapping("/majors")
    public ResponseEntity<?> list() {
        List<Map<String, Object>> labels = MajorType.getLabels();
        return ResponseEntity.ok(ApiResponse.success(labels));
    }
}
