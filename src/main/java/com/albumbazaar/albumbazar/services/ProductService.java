package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.dto.PaperDTO;
import com.albumbazaar.albumbazar.model.Cover;
import com.albumbazaar.albumbazar.model.Paper;
import com.albumbazaar.albumbazar.utilities.AllProducts;

public interface ProductService {

    AllProducts getAllProducts(final Long associationId);

    void savePaperDetailsForAssociation(Long associationId, List<PaperDTO> paperDTOs);

    void saveCoverDetailsForAssociation(Long associationId, List<CoverDTO> coverDTOs);

    Paper getPaperEntity(Long id);

    Cover getCoverEntity(Long id);

    void saveCoverDetail(final Long association, final CoverDTO coverDTO);

    void savePaperDetail(Long associationId, PaperDTO paperInfo);

    Cover changeCoverPrice(Long coverId, double price);

    Paper changePaperPrice(Long paperId, double price);

    Paper deletePaperDetail(Long paperId);

    Cover deleteCoverDetail(Long coverId);

}
