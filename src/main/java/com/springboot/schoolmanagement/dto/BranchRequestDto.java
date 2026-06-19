package com.springboot.schoolmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchRequestDto {

  @NotBlank(message = "Branch name cannot be empty")
  private String branchName;

  @NotBlank(message = "Branch code cannot be empty")
  private String branchCode;

  private String city;

  @NotNull(message = "School ID is required")
  private Long schoolId;
}
