package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.albumbazaar.albumbazar.controller.FileUploadController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
    private Double coverPrice;

    private String image;

    private Double GST;

    private Long associationId;

    private MultipartFile uploadImageFile;

    public String getImage() {
        if (image == null || image.isBlank())
            return null;

        return MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", image).build().toUri()
                .toString();
    }

}
