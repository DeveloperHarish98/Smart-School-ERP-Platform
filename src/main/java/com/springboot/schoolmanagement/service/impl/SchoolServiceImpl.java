package com.springboot.schoolmanagement.service.impl;

import com.springboot.schoolmanagement.dto.SchoolResponseDto;
import com.springboot.schoolmanagement.entity.School;
import com.springboot.schoolmanagement.exception.ResourceNotFoundException;
import com.springboot.schoolmanagement.repository.SchoolRepository;
import com.springboot.schoolmanagement.service.SchoolService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.springboot.schoolmanagement.dto.SchoolRequestDto;

@Service
public class SchoolServiceImpl implements SchoolService {

  @Autowired
  private SchoolRepository schoolRepository;

  

  @Override
  public SchoolResponseDto saveSchool(SchoolRequestDto dto) {
    School school = new School();
    school.setSchoolName(dto.getSchoolName());
    school.setSchoolLocation(dto.getLocation());
    School saved = schoolRepository.save(school);
    return mapToResponseDto(saved);
  }

  @Override
  public SchoolResponseDto updateSchool(Long id, SchoolRequestDto dto) {
    School existingSchool = schoolRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + id));
    existingSchool.setSchoolName(dto.getSchoolName());
    existingSchool.setSchoolLocation(dto.getLocation());
    School updated = schoolRepository.save(existingSchool);
    return mapToResponseDto(updated);
  }

  @Override
  public String deleteSchool(Long id) {
    if (!schoolRepository.existsById(id)) {
      throw new ResourceNotFoundException("School not found with id: " + id);
    }
    schoolRepository.deleteById(id);
    return "School Deleted Successfully";
  }


  @Override
  public Page<SchoolResponseDto> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page,size);
    Page<School> students= schoolRepository.findAll(pageable);
    return students.map(this::mapToResponseDto);
  }

  @Override
  public SchoolResponseDto schoolById(Long id) {
    School school = schoolRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + id));
    return mapToResponseDto(school);
  }

  private SchoolResponseDto mapToResponseDto(School school) {
    SchoolResponseDto responseDto = new SchoolResponseDto();
    responseDto.setId(school.getId());
    responseDto.setSchoolName(school.getSchoolName());
    responseDto.setLocation(school.getSchoolLocation());
    return responseDto;
  }

  @Override
  public List<SchoolResponseDto> findBySchoolName(String schoolName) {
    return schoolRepository
        .findBySchoolName(schoolName)
        .stream()
        .map(this::mapToResponseDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<SchoolResponseDto> findBySchoolLocation(String schoolLocation) {
    return schoolRepository
        .findBySchoolLocation(schoolLocation)
        .stream()
        .map(this::mapToResponseDto)
        .collect(Collectors.toList());
  }

}
