package com.springboot.schoolmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SchoolRequestDto {

  @NotBlank(message = "School name is required")
  private String schoolName;

  @NotBlank(message = "Location is required")
  private String location;
}
