package com.example.demo.job.batch.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step2Tasklet implements Tasklet {
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    String s3Key =
        (String)
            chunkContext
                .getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("myS3Key");

    System.out.println("Step 2 received S3 key: " + s3Key);
    return RepeatStatus.FINISHED;
  }
}
