package com.springboot.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NotBlank(message = "School name cannot be empty")
  private String schoolName;

  @Column
  @NotBlank(message = "School location is required")
  private String schoolLocation;

  @JsonIgnore
  @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
  private List<Branch> branches;
}
