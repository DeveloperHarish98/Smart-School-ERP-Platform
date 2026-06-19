package com.springboot.schoolmanagement.service.impl;

import com.springboot.schoolmanagement.dto.AuthResponseDto;
import com.springboot.schoolmanagement.dto.LoginRequestDto;
import com.springboot.schoolmanagement.dto.RegisterRequestDto;
import com.springboot.schoolmanagement.entity.User;
import com.springboot.schoolmanagement.exception.InvalidCredentialsException;
import com.springboot.schoolmanagement.repository.UserRepository;
import com.springboot.schoolmanagement.security.JwtUtils;
import com.springboot.schoolmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;

  @Override
  public String register(RegisterRequestDto dto) {

    if (userRepository.existsByUsername(dto.getUsername())) {
      throw new RuntimeException("Username already exists");
    }

    if (userRepository.existsByEmail(dto.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    User user = new User();
    user.setUsername(dto.getUsername());
    user.setEmail(dto.getEmail());
    user.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔐 hash it!
    user.setRole(dto.getRole());
    userRepository.save(user);

    return "User Registered Successfully";
  }

  @Override
  public AuthResponseDto login(LoginRequestDto dto) {

    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new InvalidCredentialsException("Email not found"));

    boolean isPasswordMatched = passwordEncoder.matches(
        dto.getPassword(),
        user.getPassword()
    );

    if (!isPasswordMatched) {
      throw new InvalidCredentialsException("Wrong password");
    }

    String token = jwtUtils.generateToken(user.getEmail(), user.getRole());

    return new AuthResponseDto(token, user.getRole());
  }
}