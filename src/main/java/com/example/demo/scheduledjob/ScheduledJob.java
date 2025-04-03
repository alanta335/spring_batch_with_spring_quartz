package com.example.demo.scheduledjob;

import com.example.demo.service.BatchService;
import java.io.Serial;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledJob implements Job, Serializable {

  @Serial
  private static final long serialVersionUID = -6273452861143339024L; // Add Serial Version UID

  private final BatchService batchService;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    batchService.startJob("job");
  }
}
