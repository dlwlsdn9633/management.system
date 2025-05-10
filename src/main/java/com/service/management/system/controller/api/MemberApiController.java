package com.service.management.system.controller.api;

import com.service.management.system.domain.member.MajorType;
import com.service.management.system.domain.member.Member;
import com.service.management.system.domain.member.MemberType;
import com.service.management.system.dto.ApiResponse;
import com.service.management.system.dto.member.MemberResponseDto;
import com.service.management.system.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{memberFk}")
    public ResponseEntity<?> read(
            @PathVariable("memberFk") int memberFk
    ) {
        Member param = new Member();
        param.setNo(memberFk);
        Member readMember = memberService.read(param);
        MemberResponseDto dto = MemberResponseDto.builder()
                .no(readMember.getNo())
                .name(readMember.getName())
                .majorTypeLabel(readMember.getMajorType().getLabel())
                .memberTypeLabel(readMember.getMemberType().getLabel())
                .build();
        return ResponseEntity.ok(ApiResponse.success(dto));
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<?> rank(
            @PathVariable("code") Integer code
    ) {
        try {
            MemberType memberType = MemberType.fromCode(code);
            Member param = Member.builder()
                    .memberType(memberType)
                    .build();
            param.setOrderByString(new String[] { "ratio DESC "});
            List<Member> memberList = memberService.list(param);
            return ResponseEntity.ok(ApiResponse.success(memberList));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(ApiResponse.failure("fail"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(ApiResponse.failure("fail"));
        }
    }
    @GetMapping("/majors")
    public ResponseEntity<?> list() {
        List<Map<String, Object>> labels = MajorType.getLabels();
        return ResponseEntity.ok(ApiResponse.success(labels));
    }

}
