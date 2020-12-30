package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private Float paperPrice;

    private Long selectedAssociationId;

}
