package com.example.demo.config;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import com.example.demo.job.scheduler.BatchSchedulerServiceImpl;
import org.quartz.*;
import org.springframework.stereotype.Component;

//@Configuration
@Component
public class JobConfig {
//  @Bean
  public JobDetail jobDetail() {
    JobDataMap jobDataMap = new JobDataMap();
    return JobBuilder.newJob(BatchSchedulerServiceImpl.class)
        .usingJobData(jobDataMap)
        .storeDurably()
        .withIdentity("Qrtz_Job_Detail")
        .withDescription("Invoke Sample Job service...")
        .build();
  }

//  @Bean
  public Trigger trigger() {
    return TriggerBuilder.newTrigger()
        .forJob(jobDetail())
        .withIdentity("Qrtz_Trigger")
        .withDescription("Sample trigger")
        .withSchedule(simpleSchedule().repeatForever().withIntervalInMinutes(2))
        .build();
  }
}
