package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    private Long associationId;

}
