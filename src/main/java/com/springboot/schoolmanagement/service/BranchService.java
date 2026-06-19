package com.springboot.schoolmanagement.service;

import com.springboot.schoolmanagement.dto.BranchRequestDto;
import com.springboot.schoolmanagement.dto.BranchResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface BranchService {

  BranchResponseDto saveBranch(BranchRequestDto dto);
  BranchResponseDto updateBranch(Long id, BranchRequestDto dto);
  BranchResponseDto branchById(Long id);
  Page<BranchResponseDto> findAll(int page, int size);
  String deleteBranch(Long id);
  List<BranchResponseDto> findByBranchName(String branchName);
  List<BranchResponseDto> findByBranchCode(String branchcode);
  
}
