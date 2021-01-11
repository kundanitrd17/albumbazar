package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.services.storage.StorageFileNotFoundException;
import com.albumbazaar.albumbazar.services.storage.StorageService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public final class FileUploadController {

    private final StorageService imageStorageService;

    @Autowired
    public FileUploadController(@Qualifier("imageStorageService") StorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/upload")
    public String uploader() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@ModelAttribute form coversList) {

        System.out.println(coversList);
        try {
            // for (MultipartFile file : files) {
            // imageStorageService.store(f.getPhoto(), f.getPhoto().getOriginalFilename());
            // }
        } catch (Exception e) {
            return "Exception";
        }
        return "Done";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = imageStorageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFoundException(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class form {
    List<cv> covers;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class cv {
    String name;
    MultipartFile photo;
}