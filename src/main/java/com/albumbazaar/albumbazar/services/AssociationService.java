package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;

import org.springframework.web.multipart.MultipartFile;

public interface AssociationService {

    boolean addAssociation(final AssociationDetailForm associationDetail);

    List<Association> getAllAssociation();

    Association getAssociation(final Long id);

    Association deleteAssociation(final Long id);

    Association restoreAssociation(final Long id);

    void updateAssociation(final Association association);

    List<Association> getAssociationWithStatus(boolean status);

    void changeProfilePhoto(MultipartFile photoFile, Long associationId);

}
