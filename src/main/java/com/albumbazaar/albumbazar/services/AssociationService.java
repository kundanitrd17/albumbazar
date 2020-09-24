package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.AssociationRepository;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
@Qualifier("associationService")
public class AssociationService {

    private final AssociationRepository associationRepository;

    @Autowired
    public AssociationService(final AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

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

    public List<Association> getAllAssociation() {
        return associationRepository.findAll().stream().collect(Collectors.toList());

    }

    public Association getAssociation(final Long id) throws NoSuchElementException {
        final Association association = associationRepository.findById(id).get();

        return association;
    }

    public void deleteAssociation(final Long id) {
        associationRepository.deleteById(id);
    }

    public void updateAssociation(final Association association) {
        associationRepository.save(association);
    }

}
