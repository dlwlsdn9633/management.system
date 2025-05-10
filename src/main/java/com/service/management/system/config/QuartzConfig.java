package com.service.management.system.config;

import com.service.management.system.scheduler.job.AlertJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail alertJobDetail() {
        return JobBuilder.newJob(AlertJob.class)
                .withIdentity("alert")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger alertJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(alertJobDetail())
                .withIdentity("alertTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
    }
}
