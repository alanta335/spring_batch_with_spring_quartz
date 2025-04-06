package com.example.demo.job.scheduler;

import com.example.demo.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchSchedulerServiceImpl implements Job {

  private final BatchService batchService;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    batchService.startJob("job");
  }
}
