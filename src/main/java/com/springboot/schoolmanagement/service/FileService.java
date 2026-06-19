package com.springboot.schoolmanagement.service;

import com.springboot.schoolmanagement.dto.FileMetadataResponseDto;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  /** Upload file to disk and record metadata in DB. Returns the saved record. */
  FileMetadataResponseDto uploadFile(MultipartFile file) throws IOException;

  /** Stream the file back as a Resource. Looks up path from DB by fileName. */
  Resource downloadFile(String fileName) throws IOException;

  /** List all uploaded files (from DB). */
  List<FileMetadataResponseDto> getAllFiles();
}
