package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dao.CoverRepository;
import com.albumbazaar.albumbazar.dao.PaperRepository;
import com.albumbazaar.albumbazar.dao.ProductCategoryRepository;
import com.albumbazaar.albumbazar.model.Cover;
import com.albumbazaar.albumbazar.model.Paper;
import com.albumbazaar.albumbazar.model.ProductCategory;
import com.albumbazaar.albumbazar.utilities.AllProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("productService")
public class ProductService {

    private ProductCategoryRepository productCategoryRepository;
    private CoverRepository coverRepository;
    private PaperRepository paperRepository;

    @Autowired
    protected ProductService(final ProductCategoryRepository productCategoryRepository,
            final CoverRepository coverRepository, final PaperRepository paperRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.coverRepository = coverRepository;
        this.paperRepository = paperRepository;
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

}
