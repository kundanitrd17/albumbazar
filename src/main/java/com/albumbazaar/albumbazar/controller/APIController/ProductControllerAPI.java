package com.albumbazaar.albumbazar.controller.APIController;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.dto.PaperDTO;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;
import com.albumbazaar.albumbazar.utilities.AllProducts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public final class ProductControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(ProductControllerAPI.class);

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public ProductControllerAPI(@Qualifier("associationService") AssociationService associationService,
            @Qualifier("productService") ProductService productService) {
        this.productService = productService;
        this.associationService = associationService;
    }

    @GetMapping(value = "/product/company")
    public ResponseEntity<?> getCompanies() {
        final List<Association> associations = associationService.getAssociationWithStatus(true).stream()
                .map(association -> {
                    Association eachAssociation = new Association();
                    eachAssociation.setId(association.getId());
                    eachAssociation.setName(association.getName());
                    return eachAssociation;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(new AbstractMap.SimpleImmutableEntry<>("data", associations));
    }

    @GetMapping(value = "/product/company/{companyId}")
    public ResponseEntity<?> getAllProductsAssociated(@PathVariable("companyId") String companyId) {
        System.out.println(companyId);

        AllProducts products = productService.getAllProducts(companyId);
        System.out.println(products);
        System.out.println(companyId);

        return ResponseEntity.ok(products);
    }

    @PostMapping(value = "/superuser/product/cover/add")
    public ResponseEntity<?> uploadCover(@ModelAttribute final CoverDTO coverInfo) {

        try {
            System.out.println(coverInfo);
            productService.saveCoverDetail(coverInfo.getAssociationId(), coverInfo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/superuser/product/paper/add")
    public ResponseEntity<?> uploadPaper(@ModelAttribute final PaperDTO paperInfo) {

        try {
            System.out.println(paperInfo);
            productService.savePaperDetail(paperInfo.getAssociationId(), paperInfo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/superuser/paper/price/{paper_id}")
    public ResponseEntity<?> changePaperPrice(@PathVariable("paper_id") final Long paperId,
            @RequestBody final Float price) {

        try {
            productService.changePaperPrice(paperId, price);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/superuser/cover/price/{cover_id}")
    public ResponseEntity<?> changeCoverPrice(@PathVariable("cover_id") final Long coverId,
            @RequestBody final Float price) {

        try {
            productService.changeCoverPrice(coverId, price);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/superuser/paper/{paper_id}")
    public ResponseEntity<?> deletePaper(@PathVariable("paper_id") final Long paperId) {

        try {
            productService.deletePaperDetail(paperId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping(value = "/superuser/cover/{cover_id}")
    public ResponseEntity<?> deleteCover(@PathVariable("cover_id") final Long coverId) {

        try {
            productService.deleteCoverDetail(coverId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping(value = "/product/paper/{paper_id}")
    public ResponseEntity<?> paperDetailsWithId(@PathVariable("paper_id") final Long paperId) {

        return ResponseEntity.ok().body(productService.getPaperEntity(paperId));

    }

}
