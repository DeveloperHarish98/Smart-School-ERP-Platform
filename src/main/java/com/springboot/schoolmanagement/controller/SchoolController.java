package com.springboot.schoolmanagement.controller;

import com.springboot.schoolmanagement.dto.SchoolRequestDto;
import com.springboot.schoolmanagement.dto.SchoolResponseDto;
import com.springboot.schoolmanagement.service.SchoolService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schools")
public class SchoolController {

  @Autowired
  private SchoolService schoolService;

  @PostMapping
  public SchoolResponseDto saveSchool(@Valid @RequestBody SchoolRequestDto dto){
    return schoolService.saveSchool(dto);
  }

  @PutMapping("/{id}")
  public SchoolResponseDto updateSchool(@PathVariable Long id, @Valid @RequestBody SchoolRequestDto dto){
    return schoolService.updateSchool(id, dto);
  }

  @GetMapping("/{id}")
  public SchoolResponseDto schoolById(@PathVariable Long id){
    return schoolService.schoolById(id);
  }

  @GetMapping
  public Page<SchoolResponseDto> findAll(int page, int size){
    return schoolService.findAll(page, size);
  }

  @DeleteMapping("/{id}")
  public String deleteSchool(@PathVariable Long id){
    return schoolService.deleteSchool(id);
  }

  @GetMapping("/SchoolName")
  public List<SchoolResponseDto> findBySchoolName(@RequestParam String schoolName){
    return schoolService.findBySchoolName(schoolName);
  }

  @GetMapping("/LocationName")
  public List<SchoolResponseDto> findBySchoolLocation(@RequestParam String schoolLocation){
    return schoolService.findBySchoolLocation(schoolLocation);
  }
}
