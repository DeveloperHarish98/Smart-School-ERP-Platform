package com.springboot.schoolmanagement.service.impl;

import com.springboot.schoolmanagement.dto.StudentRequestDto;
import com.springboot.schoolmanagement.dto.StudentResponseDto;
import com.springboot.schoolmanagement.entity.Branch;
import com.springboot.schoolmanagement.entity.Student;
import com.springboot.schoolmanagement.exception.ResourceNotFoundException;
import com.springboot.schoolmanagement.repository.BranchRepository;
import com.springboot.schoolmanagement.repository.StudentRepository;
import com.springboot.schoolmanagement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private BranchRepository branchRepository;

  @Override
  public StudentResponseDto saveStudent(StudentRequestDto dto) {
    Branch branch = branchRepository.findById(dto.getBranchId())
        .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + dto.getBranchId()));

    Student student = new Student();
    student.setStudentName(dto.getStudentName());
    student.setAge(dto.getAge());
    student.setCourse(dto.getCourse());
    student.setBranch(branch);

    Student saved = studentRepository.save(student);
    return mapToResponseDto(saved);
  }

  @Override
  public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
    Student existing = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

    Branch branch = branchRepository.findById(dto.getBranchId())
        .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + dto.getBranchId()));

    existing.setStudentName(dto.getStudentName());
    existing.setAge(dto.getAge());
    existing.setCourse(dto.getCourse());
    existing.setBranch(branch);

    Student updated = studentRepository.save(existing);
    return mapToResponseDto(updated);
  }

  @Override
  public StudentResponseDto studentById(Long id) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    return mapToResponseDto(student);
  }

  @Override
  public Page<StudentResponseDto> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Student> students = studentRepository.findAll(pageable);
    return students.map(this::mapToResponseDto);
  }

  @Override
  public String deleteStudent(Long id) {
    if (!studentRepository.existsById(id)) {
      throw new ResourceNotFoundException("Student not found with id: " + id);
    }
    studentRepository.deleteById(id);
    return "Student Deleted Successfully";
  }

  private StudentResponseDto mapToResponseDto(Student student) {
    StudentResponseDto responseDto = new StudentResponseDto();
    responseDto.setId(student.getId());
    responseDto.setStudentName(student.getStudentName());
    responseDto.setAge(student.getAge());
    responseDto.setCourse(student.getCourse());
    if (student.getBranch() != null) {
      responseDto.setBranchId(student.getBranch().getId());
    }
    return responseDto;
  }

  @Override
  public List<StudentResponseDto> findByStudentName(String studentName) {

    return studentRepository
        .findByStudentName(studentName)
        .stream()
        .map(this::mapToResponseDto)
        .toList();
  }

  @Override
  public List<StudentResponseDto> findByCourse(String course){

    return studentRepository
        .findByCourse(course)
        .stream()
        .map(this::mapToResponseDto)
        .toList();
  }

  @Override
  public List<StudentResponseDto> findByBranchId(Long id) {

    return studentRepository
        .findByBranchId(id)
        .stream()
        .map(this::mapToResponseDto)
        .toList();
  }

}
