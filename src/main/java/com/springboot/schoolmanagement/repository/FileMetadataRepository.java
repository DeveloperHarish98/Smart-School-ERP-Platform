package com.springboot.schoolmanagement.repository;

import com.springboot.schoolmanagement.entity.FileMetadata;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {

  Optional<FileMetadata> findByStoredFileName(String storedFileName);
}
