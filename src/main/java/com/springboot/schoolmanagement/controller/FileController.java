package com.springboot.schoolmanagement.controller;

import com.springboot.schoolmanagement.dto.FileMetadataResponseDto;
import com.springboot.schoolmanagement.service.FileService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

  @Autowired
  private FileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<FileMetadataResponseDto> uploadFile(
      @RequestParam("file") MultipartFile file) throws IOException {

    FileMetadataResponseDto response = fileService.uploadFile(file);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<Resource> downloadFile(
      @PathVariable String fileName) throws IOException {

    Resource resource = fileService.downloadFile(fileName);

    String contentType;
    try {
      contentType = resource.getURL().openConnection().getContentType();
    } catch (IOException e) {
      contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }
    if (contentType == null || contentType.isBlank()) {
      contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @GetMapping
  public ResponseEntity<List<FileMetadataResponseDto>> getAllFiles() {
    return ResponseEntity.ok(fileService.getAllFiles());
  }
}