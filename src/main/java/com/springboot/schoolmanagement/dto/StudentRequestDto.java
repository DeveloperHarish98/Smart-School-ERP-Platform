package com.springboot.schoolmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequestDto {

  @NotBlank(message = "Student name cannot be empty")
  private String studentName;

  @Min(value = 1, message = "Age must be positive")
  private int age;

  private String course;

  @NotNull(message = "Branch ID is required")
  private Long branchId;
}
