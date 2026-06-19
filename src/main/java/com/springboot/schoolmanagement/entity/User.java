package com.springboot.schoolmanagement.entity;

import com.springboot.schoolmanagement.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "Username cannot be empty")
  private String username;

  @Column(nullable = false, unique = true)
  @NotBlank(message = "Email cannot be empty")
  @Email(message = "Invalid email format")
  private String email;

  @Column(nullable = false, unique = true)
  @NotBlank(message = "Password cannot be empty")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;
}