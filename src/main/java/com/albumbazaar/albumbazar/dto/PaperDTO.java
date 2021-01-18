package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaperDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String paperQuality;

    @NotNull
    @NotBlank
    private String paperSize;

    @NotNull
    @NotBlank
    private Double paperPrice;

    private Double GST;

    private String image;

    private Long associationId;

    // private MultipartFile uploadImageFile;
}
