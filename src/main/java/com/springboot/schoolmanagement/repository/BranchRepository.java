package com.springboot.schoolmanagement.repository;

import com.springboot.schoolmanagement.entity.Branch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

  List<Branch> findByBranchName(String branchName);
  List<Branch> findByBranchCode(String branchCode);
}
