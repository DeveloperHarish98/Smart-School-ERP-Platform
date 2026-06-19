package com.springboot.schoolmanagement.service;

import com.springboot.schoolmanagement.dto.SchoolRequestDto;
import com.springboot.schoolmanagement.dto.SchoolResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface SchoolService {

  SchoolResponseDto saveSchool(SchoolRequestDto dto);
  SchoolResponseDto updateSchool(Long id, SchoolRequestDto dto);
  SchoolResponseDto schoolById(Long id);
  Page<SchoolResponseDto> findAll(int page, int size);
  String deleteSchool(Long id);
  List<SchoolResponseDto> findBySchoolName (String schoolName);
  List<SchoolResponseDto> findBySchoolLocation (String schoolLocation);
}
