package com.springboot.schoolmanagement.service.impl;

import com.springboot.schoolmanagement.dto.FileMetadataResponseDto;
import com.springboot.schoolmanagement.entity.FileMetadata;
import com.springboot.schoolmanagement.repository.FileMetadataRepository;
import com.springboot.schoolmanagement.service.FileService;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private static final Path UPLOAD_DIR = Paths.get("uploads");

  private final FileMetadataRepository fileMetadataRepository;

  @PostConstruct
  public void init() {
    try {
      Files.createDirectories(UPLOAD_DIR);
    } catch (IOException e) {
      throw new RuntimeException("Could not create upload directory: " + UPLOAD_DIR, e);
    }
  }

  @Override
  public FileMetadataResponseDto uploadFile(MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("Cannot upload an empty file");
    }

    String originalName = file.getOriginalFilename();
    if (originalName == null || originalName.isBlank()) {
      throw new IllegalArgumentException("File name is missing");
    }

    String safeFileName = Paths.get(originalName).getFileName().toString();
    Path destination = UPLOAD_DIR.resolve(safeFileName).normalize();


    Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);


    String contentType = file.getContentType();
    if (contentType == null || contentType.isBlank()) {
      contentType = Files.probeContentType(destination);
    }
    if (contentType == null) {
      contentType = "application/octet-stream";
    }


    FileMetadata metadata = new FileMetadata();
    metadata.setOriginalFileName(originalName);
    metadata.setStoredFileName(safeFileName);
    metadata.setFilePath(destination.toAbsolutePath().toString());
    metadata.setContentType(contentType);
    metadata.setFileSize(file.getSize());
    metadata.setUploadedAt(LocalDateTime.now());

    FileMetadata saved = fileMetadataRepository.save(metadata);
    return toDto(saved);
  }


  @Override
  public Resource downloadFile(String fileName) throws IOException {
    if (fileName == null || fileName.isBlank()) {
      throw new IllegalArgumentException("File name must not be blank");
    }


    String safeFileName = Paths.get(fileName).getFileName().toString();


    FileMetadata metadata = fileMetadataRepository
        .findByStoredFileName(safeFileName)
        .orElseThrow(() -> new IOException("File not found in database: " + safeFileName));

    Path filePath = Paths.get(metadata.getFilePath());

    try {
      Resource resource = new UrlResource(filePath.toUri());
      if (!resource.exists() || !resource.isReadable()) {
        throw new IOException("File record exists in DB but file is missing on disk: " + safeFileName);
      }
      return resource;
    } catch (MalformedURLException e) {
      throw new IOException("Malformed file path: " + safeFileName, e);
    }
  }


  @Override
  public List<FileMetadataResponseDto> getAllFiles() {
    return fileMetadataRepository.findAll()
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }


  private FileMetadataResponseDto toDto(FileMetadata m) {
    FileMetadataResponseDto dto = new FileMetadataResponseDto();
    dto.setId(m.getId());
    dto.setOriginalFileName(m.getOriginalFileName());
    dto.setStoredFileName(m.getStoredFileName());
    dto.setContentType(m.getContentType());
    dto.setFileSize(m.getFileSize());
    dto.setUploadedAt(m.getUploadedAt());
    dto.setDownloadUrl("/files/download/" + m.getStoredFileName());
    return dto;
  }
}