package com.example.demo.service.impl;

import com.example.demo.service.BatchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.S3Object;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
  private final JobLauncher jobLauncher;
  private final S3Client s3Client;
  private final Job taskletJob;

  @Value("${aws.bucket}")
  private String bucketName;

  public void startJob(String jobName) {
    try {
      List<String> s3Key = listS3Keys();

      s3Key.forEach(
          key -> {
            JobParameters jobParameters =
                new JobParametersBuilder()
                    .addString("jobName", jobName)
                    .addString("s3Key", key)
                    .toJobParameters();

            try {
              jobLauncher.run(taskletJob, jobParameters);
            } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException e) {
              log.error(e.getMessage());
            } catch (Exception e) {
              log.error("Error starting job", e);
            }
          });

    } catch (Exception e) {
      log.error("Error starting job", e);
    }
  }

  public List<String> listS3Keys() {
    ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();

    return s3Client.listObjectsV2Paginator(request).contents().stream().map(S3Object::key).toList();
  }
}
