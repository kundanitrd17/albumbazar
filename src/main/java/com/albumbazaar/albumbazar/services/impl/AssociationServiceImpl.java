package com.albumbazaar.albumbazar.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import com.albumbazaar.albumbazar.dao.AssociationRepository;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("associationService")
public class AssociationServiceImpl implements AssociationService {
    private final Logger logger = LoggerFactory.getLogger(AssociationServiceImpl.class);

    private final AssociationRepository associationRepository;

    // Dependent services
    private final StorageService imageStorageService;

    @Autowired
    public AssociationServiceImpl(final AssociationRepository associationRepository,
            @Qualifier("imageStorageService") final StorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
        this.associationRepository = associationRepository;
    }

    @Override
    @Transactional
    public boolean addAssociation(final AssociationDetailForm associationDetail) {
        try {
            final Association association = new Association(associationDetail); // create new object

            /** validate and save */
            associationRepository.save(association); // persist the data
        } catch (ConstraintViolationException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Association> getAssociationWithStatus(final boolean status) {

        return associationRepository.findByActive(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Association> getAllAssociation() {
        return associationRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Association getAssociation(final Long id) throws NoSuchElementException {
        final Association association = associationRepository.findById(id).get();

        return association;
    }

    @Override
    public void updateAssociation(final Association association) {
        associationRepository.save(association);
    }

    @Override
    public Association deleteAssociation(final Long associationId) {
        return updateStatusOfAssociation(associationId, false);

    }

    @Override
    public Association restoreAssociation(final Long associationId) {
        return updateStatusOfAssociation(associationId, true);
    }

    private Association updateStatusOfAssociation(final Long associationId, final Boolean status) {

        try {
            final Association association = associationRepository.findById(associationId).orElseThrow();
            association.setActive(status);
            return associationRepository.save(association);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to make changes");
        }

    }

    @Override
    @Transactional
    public void changeProfilePhoto(final MultipartFile photoFile, final Long associationId) {

        final Association association = this.getAssociation(associationId);

        final String fileName = "ASSO" + association.getId() + photoFile.getOriginalFilename();

        final String saved_file_name = imageStorageService.store(photoFile, fileName);

        association.setProfilePhoto(saved_file_name);

    }

}
