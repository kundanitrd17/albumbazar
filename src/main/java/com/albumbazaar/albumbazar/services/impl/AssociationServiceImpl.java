package com.albumbazaar.albumbazar.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.albumbazaar.albumbazar.dao.AssociationRepository;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.services.AssociationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("associationService")
public class AssociationServiceImpl implements AssociationService {
    private final Logger logger = LoggerFactory.getLogger(AssociationServiceImpl.class);

    private final AssociationRepository associationRepository;

    @Autowired
    public AssociationServiceImpl(final AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    @Override
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
    public List<Association> getAllAssociation() {
        return associationRepository.findAll();

    }

    @Override
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

}
