package com.springboot.schoolmanagement.controller;

import com.springboot.schoolmanagement.dto.StudentRequestDto;
import com.springboot.schoolmanagement.dto.StudentResponseDto;
import com.springboot.schoolmanagement.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @PostMapping
  public StudentResponseDto saveStudent(@Valid @RequestBody StudentRequestDto dto) {
    return studentService.saveStudent(dto);
  }

  @PutMapping("/{id}")
  public StudentResponseDto updateStudent(@PathVariable Long id,
      @Valid @RequestBody StudentRequestDto dto) {
    return studentService.updateStudent(id, dto);
  }

  @GetMapping
  public Page<StudentResponseDto> findAll(
      @RequestParam(defaultValue = "0")
      int page,
      @RequestParam(defaultValue = "5")
      int size) {

    return studentService.findAll(page, size);
  }

  @GetMapping("/{id}")
  public StudentResponseDto findById(@PathVariable Long id) {
    return studentService.studentById(id);
  }

  @DeleteMapping("/{id}")
  public String deleteStudent(@PathVariable Long id) {
    return studentService.deleteStudent(id);
  }


  @GetMapping("/studentName")
  public List<StudentResponseDto> findByStudentName(@RequestParam String studentName) {
    return studentService.findByStudentName(studentName);
  }

  @GetMapping("/course")
  public List<StudentResponseDto> findByCourse(@RequestParam String course){
    return studentService.findByCourse(course);
  }

  @GetMapping("/branch/id")
  public List<StudentResponseDto> findByBranchId(@RequestParam Long id){
    return studentService.findByBranchId(id);
  }
}
