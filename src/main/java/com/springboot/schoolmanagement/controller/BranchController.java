package com.springboot.schoolmanagement.controller;

import com.springboot.schoolmanagement.dto.BranchRequestDto;
import com.springboot.schoolmanagement.dto.BranchResponseDto;
import com.springboot.schoolmanagement.service.BranchService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchController {

  @Autowired
  private BranchService branchService;

  @PostMapping
  public BranchResponseDto saveBranch(@Valid @RequestBody BranchRequestDto dto){
    return branchService.saveBranch(dto);
  }

  @PutMapping("/{id}")
  public BranchResponseDto updateBranch(@PathVariable Long id, @Valid @RequestBody BranchRequestDto dto){
    return branchService.updateBranch(id, dto);
  }

  @GetMapping("/{id}")
  public BranchResponseDto branchById(@PathVariable Long id){
    return branchService.branchById(id);
  }

  @GetMapping
  public Page<BranchResponseDto> findAll(int page,int size){
    return branchService.findAll(page,size);
  }

  @DeleteMapping("/{id}")
  public String deleteBranch(@PathVariable Long id){
    return branchService.deleteBranch(id);
  }

  @GetMapping("/BranchName")
  public List<BranchResponseDto> findByBranchName(@RequestParam String branchName){
    return branchService.findByBranchName(branchName);
  }

  @GetMapping("/BranchCode")
  public List<BranchResponseDto> findByBranchCode(@RequestParam String branchCode){
    return branchService.findByBranchCode(branchCode);
  }
}
