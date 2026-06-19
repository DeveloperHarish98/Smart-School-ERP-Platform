package com.springboot.schoolmanagement.repository;

import com.springboot.schoolmanagement.entity.School;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

  List<School> findBySchoolName(String schoolName);
  List<School> findBySchoolLocation(String schoolLocation);
}
