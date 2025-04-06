package com.example.demo.service.impl;

import com.example.demo.config.JobConfig;
import com.example.demo.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerServiceImpl {
  private final BatchService batchService;
  private final Scheduler scheduler;
  private final JobConfig jobConfig;

  //  @Scheduled(cron = "0/30 0/1 * 1/1 * ?")
  public void startScheduler() {
    log.info("Starting scheduler");
    batchService.startJob("job");
    log.info("Scheduler completed");
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startQuartzScheduler() throws SchedulerException {
    log.info("Starting Quartz scheduler");

    JobDetail jobDetail = jobConfig.jobDetail();
    Trigger trigger = jobConfig.trigger();
    if (!scheduler.checkExists(jobDetail.getKey())) {
      scheduler.scheduleJob(jobDetail, trigger);
    }
    log.info("Quartz scheduler completed");
  }
}
