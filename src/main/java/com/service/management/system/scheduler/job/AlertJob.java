package com.service.management.system.scheduler.job;

import com.service.management.system.domain.projectMember.ProjectMember;
import com.service.management.system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertJob implements Job {
    private final ProjectService projectService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("hihihi!");
    }
}
