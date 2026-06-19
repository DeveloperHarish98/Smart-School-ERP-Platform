package com.springboot.schoolmanagement.service;

import com.springboot.schoolmanagement.dto.AuthResponseDto;
import com.springboot.schoolmanagement.dto.LoginRequestDto;
import com.springboot.schoolmanagement.dto.RegisterRequestDto;

public interface AuthService {

  // Register still returns a plain message
  String register(RegisterRequestDto dto);

  // ✅ Login now returns AuthResponseDto (token + role) instead of a plain String
  AuthResponseDto login(LoginRequestDto dto);
}