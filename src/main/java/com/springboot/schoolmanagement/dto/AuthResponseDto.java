package com.springboot.schoolmanagement.dto;

import com.springboot.schoolmanagement.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthResponseDto {
  
  private String token;

  private Role role;
}
