package com.service.management.system.service;

import com.service.management.system.domain.member.Member;
import com.service.management.system.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public List<Member> list(Member member) {
        return memberRepository.list(member);
    }
    public Member read(Member member) {
        return memberRepository.read(member);
    }
}
