package com.service.management.system.scheduler.job;

import com.service.management.system.domain.projectMember.ProjectMember;
import com.service.management.system.mapper.ProjectMemberMapper;
import com.service.management.system.service.MailService;
import com.service.management.system.service.ProjectService;
import com.service.management.system.util.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertJob implements Job {
    public static final String JOB_NAME = "alertJob";
    public static final String TRIGGER_NAME = "alertTrigger";
    public static final String JOB_CRON_EXPRESSION = "0 0 9 * * ?";
    private final MailService mailService;
    private final ProjectMemberMapper projectMemberMapper;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        for (int remainingDay : List.of(1, 2, 3, 7, 14)) {
            ProjectMember projectMemberParam = ProjectMember.builder().daysRemaining(remainingDay).build();
            List<ProjectMember> remainingMemberList = projectMemberMapper.listRemainingMembers(projectMemberParam);
            for (ProjectMember remainingMember : remainingMemberList) {
                String to = remainingMember.getEmail();
                if ("".equals(Function.isNull(to))) {
                    log.warn("Not Valid Email Address: {}", to);
                    continue;
                }
                log.info("send: " + to);
                String subject = "프로젝트가 " + remainingDay + "일 남았습니다.";
                String text = "프로젝트가 " + remainingDay + "일 남았습니다. 서둘러주세요!";
                mailService.sendMail(to, subject, text);
            }
        }
    }
}
