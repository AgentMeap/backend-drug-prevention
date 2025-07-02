package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.response.file.ResUploadFileDTO;
import com.group7.swp391.drug_prevention.service.FileService;
import com.group7.swp391.drug_prevention.util.annotation.ApiMessage;
import com.group7.swp391.drug_prevention.util.error.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    @Value("${project.upload-file.base-uri}")
    private String baseURI;

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")
    @ApiMessage("upload single file")
    public ResponseEntity<ResUploadFileDTO> uploadFile(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam("folder") String folder) throws URISyntaxException, IOException, StorageException {
        //validate file
        if(file == null || file.isEmpty()) {
            throw new StorageException("File is empty, please select a file to upload.");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = List.of("jpg", "jpeg", "png");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> fileName.toLowerCase().endsWith(item));

        if(!isValid) {
            throw new StorageException("Invalid file type. Allowed types are: " + String.join(", ", allowedExtensions));
        }
        //create a directory if it does not exist
        this.fileService.createDirectory(baseURI + folder);

        //store file
        String uploadFile = this.fileService.store(file, folder);
        ResUploadFileDTO res = new ResUploadFileDTO(uploadFile, Instant.now());
        return ResponseEntity.ok().body(res);
    }
}
