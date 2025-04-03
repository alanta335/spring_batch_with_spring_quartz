package com.example.demo.controller;

import com.example.demo.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
@Slf4j
@RequiredArgsConstructor
public class BatchController {
  private final BatchService batchService;

  @GetMapping("/start/{jobName}")
  public String startJob(@PathVariable String jobName) {
    try {
      batchService.startJob(jobName);
    } catch (Exception e) {
      log.error("Error starting job {}", jobName, e);
    }
    log.info("Job started jobName: {}", jobName);
    return "Job started" + jobName;
  }

  @GetMapping("/test")
  public ResponseEntity<String> startJob2() {
    return ResponseEntity.ok("Batch Test Endpoint is working!");
  }
}
