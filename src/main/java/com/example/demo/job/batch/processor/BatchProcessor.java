package com.example.demo.job.batch.processor;

import com.example.demo.model.domain.Vehicle;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessor {

  public ItemProcessor<Vehicle, Vehicle> vehicleItemProcessor() {
    return new ItemProcessor<>() {
      @Override
      public Vehicle process(Vehicle item) {
        // Example: skip vehicles older than 2005

        // Add more transformation logic here if needed
        return item;
      }
    };
  }
}
