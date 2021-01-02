package com.albumbazaar.albumbazar.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.Mapper.CoverMapper;
import com.albumbazaar.albumbazar.Mapper.PaperMapper;
import com.albumbazaar.albumbazar.dao.CoverRepository;
import com.albumbazaar.albumbazar.dao.PaperRepository;
import com.albumbazaar.albumbazar.dao.ProductCategoryRepository;
import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.dto.PaperDTO;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Cover;
import com.albumbazaar.albumbazar.model.Paper;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;
import com.albumbazaar.albumbazar.utilities.AllProducts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("productService")
public class ProductServiceImpl implements ProductService {

    // Loger lagback
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    // Repositories
    private final ProductCategoryRepository productCategoryRepository;
    private final CoverRepository coverRepository;
    private final PaperRepository paperRepository;

    // dependent services
    private final AssociationService associationService;

    // Entit mappers
    private final PaperMapper paperMapper;
    private final CoverMapper coverMapper;

    @Autowired
    protected ProductServiceImpl(final ProductCategoryRepository productCategoryRepository,
            final CoverRepository coverRepository, final PaperRepository paperRepository,
            @Qualifier("associationService") final AssociationService associationService, final PaperMapper paperMapper,
            final CoverMapper coverMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.coverRepository = coverRepository;
        this.paperRepository = paperRepository;
        this.associationService = associationService;
        this.paperMapper = paperMapper;
        this.coverMapper = coverMapper;
    }

    public AllProducts getAllProducts(final String company) {
        AllProducts products = new AllProducts();
        try {
            Long id = Long.parseLong(company);

            // Flooding Categories
            products.setProductCategories(productCategoryRepository.findAllByAssociationId(id).stream()
                    .map(item -> item.getProductName()).collect(Collectors.toList()));
            // Flooding covers
            final List<Cover> covers = coverRepository.findAllByAssociationId(id);
            products.setCovers(covers);

            // Flooding papers
            List<Paper> papers = paperRepository.findAllByAssociationId(id);
            products.setPapers(papers);

            // Extracting all the size
            HashSet<String> sizes = new HashSet<>();
            for (Cover cover : covers) {
                sizes.add(cover.getCoverSize());
            }
            for (Paper paper : papers) {
                sizes.add(paper.getPaperSize());
            }

            // Flooding sizes
            products.setSizes(sizes);

            return products;

        } catch (NumberFormatException e) {
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return products;
    }

    @Override
    public void savePaperDetailsForAssociation(final Long associationId, final List<PaperDTO> paperDTOs) {

        final Association association = associationService.getAssociation(associationId);

        paperDTOs.stream().forEach(paperDTO -> {
            try {
                this.savePaperDetail(association, paperDTO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });

    }

    @Transactional
    private void savePaperDetail(final Association association, final PaperDTO paperDTO) {

        final Paper paper = paperMapper.paperDTOToPaperEntity(paperDTO);
        paper.setAssociation(association);
        paperRepository.save(paper);

    }

    @Override
    public void saveCoverDetailsForAssociation(Long associationId, List<CoverDTO> coverDTOs) {
        final Association association = associationService.getAssociation(associationId);

        coverDTOs.stream().forEach(coverDTO -> {
            try {
                saveCoverDetail(association, coverDTO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
    }

    private void saveCoverDetail(final Association association, final CoverDTO coverDTO) {
        final Cover cover = coverMapper.coverDTOToCover(coverDTO);
        cover.setAssociation(association);

        coverRepository.save(cover);
    }

    @Override
    public Paper getPaperEntity(final Long id) {
        return paperRepository.findById(id).orElseThrow();
    }

    @Override
    public Cover getCoverEntity(Long id) {
        return coverRepository.findById(id).orElseThrow();
    }

}
