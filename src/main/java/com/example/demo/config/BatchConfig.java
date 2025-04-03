package com.example.demo.config;

import com.example.demo.tasklet.Step1Tasklet;
import com.example.demo.tasklet.Step2Tasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
  private final Step1Tasklet step1Tasklet;
  private final Step2Tasklet step2Tasklet;

  @Bean
  public Job taskletJob(JobRepository jobRepository, Step step1, Step step2) {
    return new JobBuilder("job", jobRepository).start(step1).next(step2).build();
  }

  @Bean
  public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step1", jobRepository)
        .tasklet(step1Tasklet, transactionManager)
        .build();
  }

  @Bean
  public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step2", jobRepository)
        .tasklet(step2Tasklet, transactionManager)
        .build();
  }
}
