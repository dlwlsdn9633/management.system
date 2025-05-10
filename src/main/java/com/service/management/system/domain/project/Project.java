package com.service.management.system.domain.project;

import com.service.management.system.domain.Common;
import com.service.management.system.domain.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Project extends Common {
    private int areaFk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDate;
    private ProjectType projectType;
    private String contents;

    private String area;
    private String memberFks;
    private String storeFilenames;
    private List<Member> memberList;
    private int commentMaxGno;
    private int memberFk;
    @Override
    public String toString() {
        return "Project{" +
                "areaFk=" + areaFk +
                ", requestDate=" + requestDate +
                ", completeDate=" + completeDate +
                ", expectedDate=" + expectedDate +
                ", projectType=" + projectType +
                ", contents='" + contents + '\'' +
                ", area='" + area + '\'' +
                ", memberFks='" + memberFks + '\'' +
                ", storeFilenames='" + storeFilenames + '\'' +
                ", memberList=" + memberList +
                ", commentMaxGno=" + commentMaxGno +
                ", memberFk=" + memberFk +
                '}';
    }
}
