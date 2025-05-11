package com.service.management.system.config;

import com.service.management.system.scheduler.job.AlertJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail alertJobDetail() {
        return createJobDetail(AlertJob.class, AlertJob.JOB_NAME);
    }
    @Bean
    public Trigger alertJobTrigger() {
        return createTrigger(alertJobDetail(), AlertJob.TRIGGER_NAME, AlertJob.JOB_CRON_EXPRESSION);
    }



    // 공통 JobDetail 생성 메서드
    private JobDetail createJobDetail(Class<? extends Job> jobClass, String name) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(name)
                .storeDurably()
                .build();
    }
    // 공통 Trigger 생성 메서드
    private Trigger createTrigger(JobDetail jobDetail, String triggerName, String cronExpression) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(triggerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }
}
