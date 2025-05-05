package com.service.management.system.repository.member;

import com.service.management.system.domain.member.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> list(Member member);
}
