package com.albumbazaar.albumbazar.utilities;

import java.util.HashSet;
import java.util.List;

import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.dto.PaperDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllProducts {
    private List<String> productCategories;
    private List<CoverDTO> covers;
    private List<PaperDTO> papers;
    private HashSet<String> sizes;

}
