package com.service.management.system.domain.projectMember;

import com.service.management.system.domain.Common;
import com.service.management.system.domain.project.ProjectType;
import lombok.*;

// 나중에 DTO로 나누기

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMember extends Common {
    private int projectFk;
    private int memberFk;

    private String contents;
    private ProjectType projectType;
}
