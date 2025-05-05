package com.service.management.system.domain.projectArea;

import com.service.management.system.domain.Common;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectArea extends Common {
    private int projectFk;
    private int areaFk;
}
