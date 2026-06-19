package com.springboot.schoolmanagement.service.impl;

import com.springboot.schoolmanagement.dto.BranchRequestDto;
import com.springboot.schoolmanagement.dto.BranchResponseDto;
import com.springboot.schoolmanagement.entity.Branch;
import com.springboot.schoolmanagement.entity.School;
import com.springboot.schoolmanagement.exception.ResourceNotFoundException;
import com.springboot.schoolmanagement.repository.BranchRepository;
import com.springboot.schoolmanagement.repository.SchoolRepository;
import com.springboot.schoolmanagement.service.BranchService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {

  @Autowired
  private BranchRepository branchRepository;

  @Autowired
  private SchoolRepository schoolRepository;

  @Override
  public BranchResponseDto saveBranch(BranchRequestDto dto) {
    School school = schoolRepository.findById(dto.getSchoolId())
        .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + dto.getSchoolId()));

    Branch branch = new Branch();
    branch.setBranchName(dto.getBranchName());
    branch.setBranchCode(dto.getBranchCode());
    branch.setCity(dto.getCity());
    branch.setSchool(school);

    Branch saved = branchRepository.save(branch);
    return mapToResponseDto(saved);
  }

  @Override
  public BranchResponseDto updateBranch(Long id, BranchRequestDto dto) {
    Branch existing = branchRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

    School school = schoolRepository.findById(dto.getSchoolId())
        .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + dto.getSchoolId()));

    existing.setBranchName(dto.getBranchName());
    existing.setBranchCode(dto.getBranchCode());
    existing.setCity(dto.getCity());
    existing.setSchool(school);

    Branch updated = branchRepository.save(existing);
    return mapToResponseDto(updated);
  }

  @Override
  public BranchResponseDto branchById(Long id) {
    Branch branch = branchRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
    return mapToResponseDto(branch);
  }

  @Override
  public Page<BranchResponseDto> findAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page <Branch> branches = branchRepository.findAll(pageable);
    return branches.map(this::mapToResponseDto);
  }

  @Override
  public String deleteBranch(Long id) {
    if (!branchRepository.existsById(id)) {
      throw new ResourceNotFoundException("Branch not found with id: " + id);
    }
    branchRepository.deleteById(id);
    return "Branch Deleted Successfully";
  }


  private BranchResponseDto mapToResponseDto(Branch branch) {
    BranchResponseDto responseDto = new BranchResponseDto();
    responseDto.setId(branch.getId());
    responseDto.setBranchName(branch.getBranchName());
    responseDto.setBranchCode(branch.getBranchCode());
    responseDto.setCity(branch.getCity());
    if (branch.getSchool() != null) {
      responseDto.setSchoolId(branch.getSchool().getId());
    }
    return responseDto;
  }

  @Override
  public List<BranchResponseDto> findByBranchName(String branchName) {
    return branchRepository
        .findByBranchName(branchName)
        .stream()
        .map(this::mapToResponseDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<BranchResponseDto> findByBranchCode(String branchCode) {
    return branchRepository
        .findByBranchCode(branchCode)
        .stream()
        .map(this::mapToResponseDto)
        .collect(Collectors.toList());
  }

}
