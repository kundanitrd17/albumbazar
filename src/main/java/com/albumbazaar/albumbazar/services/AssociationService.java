package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;

public interface AssociationService {

    boolean addAssociation(final AssociationDetailForm associationDetail);

    List<Association> getAllAssociation();

    Association getAssociation(final Long id);

    void deleteAssociation(final Long id);

    void updateAssociation(final Association association);

    List<Association> getAssociationWithStatus(boolean status);

}
