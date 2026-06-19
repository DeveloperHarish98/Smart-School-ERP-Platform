package com.springboot.schoolmanagement.dto;

import lombok.Data;

@Data
public class StudentResponseDto {

  private Long id;
  private String studentName;
  private int age;
  private String course;
  private Long branchId;
}
