package com.service.management.system.domain.comment;

import com.service.management.system.domain.Common;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends Common {
    private int projectFk;
    private int memberFk;
    private String contents;
    // groupNo: 그룹 ID를 의미한다.
    private int groupNo;
    // step: 그룹 내 순서를 의미한다.
    private int step;
    // depth: 깊이를 의미한다.
    private int depth;
    private int commentMaxStep;
}
