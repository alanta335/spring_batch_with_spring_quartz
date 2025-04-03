package com.example.demo.service.impl;

import com.example.demo.service.BatchService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BatchServiceImpl implements BatchService {
  @Autowired private JobLauncher jobLauncher;

  @Autowired
  @Qualifier("taskletJob")
  private Job taskletJob;

  public void startJob(String jobName) {
    try {
      JobParameters jobParameters =
          new JobParametersBuilder()
              .addLong("currentTime", System.currentTimeMillis())
              .toJobParameters();
      jobLauncher.run(taskletJob, jobParameters);
    } catch (Exception e) {
      log.error("Error starting job", e);
    }
  }
}
