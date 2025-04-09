package com.example.demo.job.batch.processor;

import com.example.demo.model.domain.Vehicle;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchProcessor {

  @Bean
  public ItemProcessor<Vehicle, Vehicle> vehicleItemProcessor() {
    return vehicle -> {
      System.out.println(vehicle);
      return vehicle;
    };
  }
}
