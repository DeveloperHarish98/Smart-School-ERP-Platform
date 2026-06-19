package com.springboot.schoolmanagement.repository;

import com.springboot.schoolmanagement.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findByStudentName(String studentName);
  List<Student> findByCourse(String course);
  List<Student> findByBranchId(Long id);
}
