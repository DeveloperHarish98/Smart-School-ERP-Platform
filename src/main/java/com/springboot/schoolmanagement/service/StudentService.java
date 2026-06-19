package com.springboot.schoolmanagement.service;

import com.springboot.schoolmanagement.dto.StudentRequestDto;
import com.springboot.schoolmanagement.dto.StudentResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface StudentService {

  StudentResponseDto saveStudent(StudentRequestDto dto);
  StudentResponseDto updateStudent(Long id, StudentRequestDto dto);
  StudentResponseDto studentById(Long id);
  Page<StudentResponseDto> findAll(int page, int size);
  String deleteStudent(Long id);
  List<StudentResponseDto> findByStudentName(String studentName);
  List<StudentResponseDto> findByCourse(String course);
  List<StudentResponseDto> findByBranchId(Long id);
}
