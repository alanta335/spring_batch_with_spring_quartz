package com.example.demo.config;

import com.example.demo.job.batch.Step1Tasklet;
import com.example.demo.listener.FileInjectionListener;
import com.example.demo.model.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
  private final Step1Tasklet step1Tasklet;
  private final FlatFileItemReader<Vehicle> csvFromS3Reader;
  private final FileInjectionListener fileInjectionListener;

  @Bean
  public Job taskletJob(
      JobRepository jobRepository, Step fetchS3FileToProcessStep, Step processCsvFileStep) {
    return new JobBuilder("job", jobRepository)
        .start(fetchS3FileToProcessStep)
        .next(processCsvFileStep)
        .build();
  }

  @Bean
  public Step fetchS3FileToProcessStep(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step1", jobRepository)
        .tasklet(step1Tasklet, transactionManager)
        .build();
  }

  @Bean
  public Step processCsvFileStep(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step2", jobRepository)
        .chunk(3, transactionManager)
        .listener(fileInjectionListener)
        .reader(csvFromS3Reader)
        .writer(items -> {})
        .build();
  }
}
