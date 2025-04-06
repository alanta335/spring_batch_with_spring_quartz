package com.example.demo.listener;

import com.example.demo.model.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileInjectionListener implements StepExecutionListener {

  private final FlatFileItemReader<Vehicle> csvFromS3Reader;

  @Override
  public void beforeStep(StepExecution stepExecution) {
    log.info("Before step: {}", stepExecution.getStepName());
    String path =
        stepExecution.getJobExecution().getExecutionContext().getString("downloadedFilePath");
    csvFromS3Reader.setResource(new FileSystemResource(path));
    log.info("setting resource to: {} completed", path);
  }
}
