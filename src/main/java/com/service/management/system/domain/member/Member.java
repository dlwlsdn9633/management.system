package com.service.management.system.domain.member;

import com.service.management.system.domain.Common;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Member extends Common {
    private String id;
    private String email;
    private String password;
    private MemberType memberType;
    private MajorType majorType;
}
