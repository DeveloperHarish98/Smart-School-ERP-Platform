package com.springboot.schoolmanagement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMetadataResponseDto {

  private Long id;
  private String originalFileName;
  private String storedFileName;
  private String contentType;
  private Long fileSize;
  private LocalDateTime uploadedAt;
  private String downloadUrl;
}
