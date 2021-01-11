package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String coverName;

    @NotNull
    @NotBlank
    private String coverSize;

    @NotNull
    @NotBlank
    private Float coverPrice;

    private String image;

    private Float GST;

    private Long associationId;

    private MultipartFile uploadImageFile;

}
