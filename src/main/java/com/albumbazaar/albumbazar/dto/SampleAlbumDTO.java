package com.albumbazaar.albumbazar.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SampleAlbumDTO {
    
    private Long id;

    private String title;
    private String description;

    private MultipartFile image;

}
