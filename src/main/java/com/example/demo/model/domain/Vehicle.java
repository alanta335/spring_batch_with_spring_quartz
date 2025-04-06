package com.example.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
  private String name;
  private String year;
  private String kmDriven;
  private String fuel;
  private String sellerType;
  private String transmission;
  private String owner;
  private String sellingPrice;
}
