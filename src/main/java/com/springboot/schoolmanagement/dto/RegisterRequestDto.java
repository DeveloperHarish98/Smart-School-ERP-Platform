package com.springboot.schoolmanagement.dto;

import com.springboot.schoolmanagement.Enum.Role;
import lombok.Data;

@Data
public class RegisterRequestDto {

  private String username;
  private String email;
  private String password;
  private Role role;
}