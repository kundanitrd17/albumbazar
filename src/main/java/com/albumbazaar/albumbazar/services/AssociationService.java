package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.AssociationRepository;
import com.albumbazaar.albumbazar.model.Association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("associationService")
public class AssociationService {

    private final AssociationRepository associationRepository;
    @Autowired
    public AssociationService(final AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    public boolean addAssociation(/* Take form */) {
        final Association association = new Association();

        /** validate and save */
        associationRepository.save(association);
        return true;
    }
    public Optional<List<Association>> getAllAssociation() {
        final List<Association> associations = associationRepository.findAll().stream().collect(Collectors.toList());

        return Optional.of(associations);
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
