package com.springboot.schoolmanagement.controller;

import com.springboot.schoolmanagement.dto.AuthResponseDto;
import com.springboot.schoolmanagement.dto.LoginRequestDto;
import com.springboot.schoolmanagement.dto.RegisterRequestDto;
import com.springboot.schoolmanagement.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto dto) {
    String message = authService.register(dto);
    return ResponseEntity.ok(message);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
    AuthResponseDto response = authService.login(dto);
    return ResponseEntity.ok(response);
  }
}