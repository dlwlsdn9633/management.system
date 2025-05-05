package com.service.management.system.domain.projectMember;

import com.service.management.system.domain.Common;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMember extends Common {
    private int projectNo;
    private int memberNo;
}
