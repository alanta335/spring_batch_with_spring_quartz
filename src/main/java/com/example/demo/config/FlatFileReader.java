package com.example.demo.config;

import com.example.demo.model.domain.Vehicle;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlatFileReader {

  @Bean
  public FlatFileItemReader<Vehicle> csvFromS3Reader() {
    FlatFileItemReader<Vehicle> reader = new FlatFileItemReader<>();
    //    reader.setResource(new FileSystemResource(new File(filePath)));
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
