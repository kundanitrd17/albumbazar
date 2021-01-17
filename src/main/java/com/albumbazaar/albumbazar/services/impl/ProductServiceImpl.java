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
import com.albumbazaar.albumbazar.services.storage.ImageStorageService;
import com.albumbazaar.albumbazar.services.storage.StorageService;
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
    private final StorageService imageStorageService;

    // Entit mappers
    private final PaperMapper paperMapper;
    private final CoverMapper coverMapper;

    @Autowired
    protected ProductServiceImpl(final ProductCategoryRepository productCategoryRepository,
            final CoverRepository coverRepository, final PaperRepository paperRepository,
            @Qualifier("associationService") final AssociationService associationService, final PaperMapper paperMapper,
            final CoverMapper coverMapper, @Qualifier("imageStorageService") final StorageService imageStorageService) {
        this.productCategoryRepository = productCategoryRepository;
        this.coverRepository = coverRepository;
        this.paperRepository = paperRepository;
        this.associationService = associationService;
        this.paperMapper = paperMapper;
        this.coverMapper = coverMapper;
        this.imageStorageService = imageStorageService;
    }

    public AllProducts getAllProducts(final String company) {
        AllProducts products = new AllProducts();
        try {
            final Long associationId = Long.parseLong(company);

            final Association association = associationService.getAssociation(associationId);

            // Flooding Categories
            products.setProductCategories(productCategoryRepository.findAllByAssociationAndActive(association, true)
                    .stream().map(item -> item.getProductName()).collect(Collectors.toList()));

            final List<Cover> covers = coverRepository.findAllByAssociationAndActive(association, true);
            final List<Paper> papers = paperRepository.findAllByAssociationAndActive(association, true);

            // Extracting all the size
            HashSet<String> sizes = new HashSet<>();
            covers.stream().forEach(cover -> sizes.add(cover.getCoverSize()));

            // Paper sizes need not be included
            // papers.stream().forEach(paper -> sizes.add(paper.getPaperSize()));

            // Flooding sizes
            products.setSizes(sizes);
            // Flooding covers
            products.setCovers(covers.stream().map(coverMapper::coverTCoverDTO).collect(Collectors.toList()));
            // Flooding papers

            products.setPapers(papers.stream().map(paperMapper::paperEntityToPaperDTO).collect(Collectors.toList()));

            return products;

        } catch (NumberFormatException e) {
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return products;
    }

    // @Override
    // public void savePaperDetailsForAssociation(final Long associationId, final
    // List<PaperDTO> paperDTOs) {

    // final Association association =
    // associationService.getAssociation(associationId);

    // paperDTOs.stream().forEach(paperDTO -> {
    // try {
    // this.savePaperDetail(association, paperDTO);
    // } catch (Exception e) {
    // logger.error(e.getMessage());
    // }
    // });

    // }

    @Override
    public void saveCoverDetailsForAssociation(Long associationId, List<CoverDTO> coverDTOs) {
        // final Association association = associationService.getAssociation();

        coverDTOs.stream().forEach(coverDTO -> {
            try {
                saveCoverDetail(associationId, coverDTO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
    }

    @Override
    @Transactional
    public void saveCoverDetail(final Long associationId, final CoverDTO coverDTO) {

        final Association association = associationService.getAssociation(associationId);

        System.out.println(coverDTO.getUploadImageFile().getOriginalFilename());
        final Cover cover = coverMapper.coverDTOToCover(coverDTO);
        cover.setAssociation(association);

        final Cover saved_cover = coverRepository.save(cover);

        String fileName = "cover" + saved_cover.getId() + coverDTO.getUploadImageFile().getOriginalFilename();
        fileName = imageStorageService.store(coverDTO.getUploadImageFile(), fileName);

        saved_cover.setImage(fileName);

    }

    @Override
    @Transactional
    public void savePaperDetail(final Long associationId, final PaperDTO paperDTO) {

        final Association association = associationService.getAssociation(associationId);

        final Paper paper = paperMapper.paperDTOToPaperEntity(paperDTO);
        paper.setAssociation(association);

        paperRepository.save(paper);

    }

    @Override
    public Paper getPaperEntity(final Long id) {
        return paperRepository.findById(id).orElseThrow();
    }

    @Override
    public Cover getCoverEntity(Long id) {
        return coverRepository.findById(id).orElseThrow();
    }

    @Override
    public void savePaperDetailsForAssociation(Long associationId, List<PaperDTO> paperDTOs) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional
    public void changeCoverPrice(final Long coverId, final float price) {

        if (price <= 0f) {
            throw new RuntimeException("Price is invalid");
        }

        final Cover cover = coverRepository.findById(coverId).orElseThrow();

        cover.setCoverPrice(price);

    }

    @Override
    @Transactional
    public void changePaperPrice(final Long paperId, final float price) {
        if (price <= 0f) {
            throw new RuntimeException("Price is invalid");
        }

        final Paper paper = paperRepository.findById(paperId).orElseThrow();

        paper.setPaperPrice(price);

    }

    @Override
    @Transactional
    public void deletePaperDetail(final Long paperId) {

        final Paper paper = paperRepository.findById(paperId).orElseThrow();

        paper.setActive(false);

    }

    @Override
    @Transactional
    public void deleteCoverDetail(final Long coverId) {

        final Cover cover = coverRepository.findById(coverId).orElseThrow();

        cover.setActive(false);

    }

}
