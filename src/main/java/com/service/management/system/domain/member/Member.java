package com.service.management.system.domain.member;

import com.service.management.system.domain.Common;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Member extends Common {
    private String id;
    private String password;
    private MemberType memberType;
}
