package com.javachallenge.fileapi.api.controller;

import com.javachallenge.fileapi.business.FileService;
import com.javachallenge.fileapi.business.validation.ValidateFile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;


@RestController
@RequestMapping("/files")
@Validated
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;

    private final String NOT_VALID_ID = "ID should be greater than 0!";


    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("file") @ValidateFile MultipartFile file) {
        fileService.fileUpload(file);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllFileInformationPage(Pageable pageable) {
        return ResponseEntity.ok(fileService.getAllFileInformation(pageable));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> getFileById(@PathVariable("id") @Min(value = 1, message = NOT_VALID_ID) Long fileId) {
        return ResponseEntity.ok(fileService.getFileById(fileId));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFileById(@PathVariable("id") @Min(value = 1, message = NOT_VALID_ID) Long fileId) {
        fileService.deleteFileById(fileId);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFileById(@RequestParam("file") @ValidateFile MultipartFile file,
                               @PathVariable("id") @Min(value = 1, message = NOT_VALID_ID) Long fileId) {
        fileService.updateFileById(file, fileId);
    }


}
