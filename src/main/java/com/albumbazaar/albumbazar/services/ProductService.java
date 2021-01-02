package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.dto.PaperDTO;
import com.albumbazaar.albumbazar.model.Cover;
import com.albumbazaar.albumbazar.model.Paper;
import com.albumbazaar.albumbazar.utilities.AllProducts;

public interface ProductService {

    AllProducts getAllProducts(final String company);

    void savePaperDetailsForAssociation(Long associationId, List<PaperDTO> paperDTOs);

    void saveCoverDetailsForAssociation(Long associationId, List<CoverDTO> coverDTOs);

    Paper getPaperEntity(Long id);

    Cover getCoverEntity(Long id);

}
