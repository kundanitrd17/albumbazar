package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.OrderDetail;

import org.springframework.web.multipart.MultipartFile;

public interface AssociationService {

    Association addAssociation(final AssociationDetailForm associationDetail);

    List<Association> getAllAssociation();

    Association getAssociation(final Long id);

    Association deleteAssociation(final Long id);

    Association restoreAssociation(final Long id);

    void updateAssociation(final Association association);

    List<Association> getAssociationWithStatus(boolean status);

    public void changeProfilePhoto(final MultipartFile photoFile, final Long associationId);

    List<OrderDetail> getAllNewlyArrivedOrders(Long associationId);

    List<OrderDetail> getUnderProcessOrders(Long associationId);

    List<OrderDetail> getReadyToDeliverOrders(Long associationId);

    List<OrderDetail> getCompletedOrder(Long associationId);

    void acceptOrder(Long orderId);

    void setOrderCompleted(Long orderId);

    void processForDelivery(Long orderId);

}
