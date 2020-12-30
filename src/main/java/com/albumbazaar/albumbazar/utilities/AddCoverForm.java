package com.albumbazaar.albumbazar.utilities;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.albumbazaar.albumbazar.dto.CoverDTO;

import lombok.Data;

@Data
public class AddCoverForm {

    @NotNull
    private Long associationId;
    private List<CoverDTO> coverDetails;

}
