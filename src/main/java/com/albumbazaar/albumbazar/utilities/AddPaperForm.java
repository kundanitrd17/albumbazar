package com.albumbazaar.albumbazar.utilities;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.albumbazaar.albumbazar.dto.PaperDTO;

import lombok.Data;

@Data
public class AddPaperForm {
    @NotNull
    private Long associationId;
    private List<PaperDTO> paperDetails;
}
