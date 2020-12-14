package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.storage.StorageFileNotFoundException;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public final class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(@Qualifier("imageStorageService") StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload")
    public String uploader() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("files") MultipartFile files) {

        try {
            storageService.store(files);
        } catch (Exception e) {
            return "Exception";
        }
        return "Done";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFoundException(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}