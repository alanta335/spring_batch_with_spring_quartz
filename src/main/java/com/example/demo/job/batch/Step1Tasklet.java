package com.example.demo.job.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class Step1Tasklet implements Tasklet {

  private final S3Client s3Client;

  @Value("${aws.bucket}")
  private String bucket;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    // Access job parameters
    Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();

    String s3Key = (String) jobParameters.get("s3Key");

    log.info("Running Tasklet with S3 Key: {}", s3Key);

    try {
      // Create a temp file
      File tempFile = Files.createTempFile("s3-file-", ".csv").toFile();

      log.info("Downloading file from S3: s3://{}/{}", bucket, s3Key);

      // Get object from S3
      GetObjectRequest request = GetObjectRequest.builder().bucket(bucket).key(s3Key).build();

      try (ResponseInputStream<GetObjectResponse> s3InputStream = s3Client.getObject(request)) {
        Files.copy(s3InputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }

      log.info("Downloaded file saved to: {}", tempFile.getAbsolutePath());

      // Save path to execution context for next step
      chunkContext
          .getStepContext()
          .getStepExecution()
          .getJobExecution()
          .getExecutionContext()
          .put("downloadedFilePath", tempFile.getAbsolutePath());

    } catch (Exception e) {
      log.error("Failed to download S3 file", e);
      throw new RuntimeException(e);
    }
    // Save the key in job execution context to share with next step
    chunkContext
        .getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext()
        .put("myS3Key", s3Key);
    // You can now process the file/key here

    return RepeatStatus.FINISHED;
  }
}
