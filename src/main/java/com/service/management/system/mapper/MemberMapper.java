package com.service.management.system.mapper;

import com.service.management.system.domain.member.Member;
import java.util.List;

public interface MemberMapper {
    List<Member> list(Member member);
    Member read(Member member);
}
