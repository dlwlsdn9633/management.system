package com.service.management.system.domain.area;

import com.service.management.system.domain.Common;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area extends Common {
    private String contents;
    private int areaType;
    private int relNo;
}
