package com.springboot.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "branch_id")
  private Long id;

  @Column
  @NotBlank(message = "Branch name cannot be empty")
  private String branchName;

  @Column
  @NotBlank(message = "Branch code cannot be empty")
  private String branchCode;

  private String city;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "school_id")
  private School school;

  @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
  private List<Student> students;
}
