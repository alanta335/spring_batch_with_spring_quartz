package com.example.demo.job.batch.reader;

import com.example.demo.model.domain.Vehicle;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FlatFileReader {

  @Bean
  @StepScope
  public FlatFileItemReader<Vehicle> csvFromS3Reader(
      @Value("#{jobExecutionContext['downloadedFilePath']}") String filePath) {
    FlatFileItemReader<Vehicle> reader = new FlatFileItemReader<>();
    reader.setResource(new FileSystemResource(filePath));

    DefaultLineMapper<Vehicle> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setNames(
        "name",
        "year",
        "selling_price",
        "km_driven",
        "fuel",
        "seller_type",
        "transmission",
        "owner");
    lineMapper.setLineTokenizer(tokenizer);
    BeanWrapperFieldSetMapper<Vehicle> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Vehicle.class);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);

    reader.setLinesToSkip(1); // Skip header
    return reader;
  }
}
