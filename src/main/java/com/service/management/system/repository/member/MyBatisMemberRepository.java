package com.service.management.system.repository.member;

import com.service.management.system.domain.member.Member;
import com.service.management.system.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {
    private final MemberMapper memberMapper;
    @Override
    public List<Member> list(Member member) {
        return memberMapper.list(member);
    }
}
