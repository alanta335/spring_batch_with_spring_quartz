package com.example.demo.service.impl;

import com.example.demo.config.QuartzJobConfig;
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
  private final Scheduler scheduler;
  private final QuartzJobConfig quartzJobConfig;

  @EventListener(ApplicationReadyEvent.class)
  public void startQuartzScheduler() throws SchedulerException {
    log.info("Starting Quartz scheduler");

    JobDetail jobDetail = quartzJobConfig.jobDetail();
    Trigger trigger = quartzJobConfig.trigger();
    if (!scheduler.checkExists(jobDetail.getKey())) {
      scheduler.scheduleJob(jobDetail, trigger);
    }
    log.info("Quartz scheduler completed");
  }
}
