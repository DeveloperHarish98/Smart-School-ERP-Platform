package com.springboot.schoolmanagement.dto;

import lombok.Data;

@Data
public class BranchResponseDto {

  private Long id;
  private String branchName;
  private String branchCode;
  private String city;
  private Long schoolId;
}
