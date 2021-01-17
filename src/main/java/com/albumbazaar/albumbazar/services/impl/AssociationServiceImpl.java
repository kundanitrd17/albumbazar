package com.albumbazaar.albumbazar.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import com.albumbazaar.albumbazar.dao.AssociationRepository;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.storage.ImageStorageService;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("associationService")
public class AssociationServiceImpl implements AssociationService {
    private final Logger logger = LoggerFactory.getLogger(AssociationServiceImpl.class);

    private final AssociationRepository associationRepository;

    private final StorageService imageStorageService;
    private final ApplicationContext applicationContext;

    @Autowired
    public AssociationServiceImpl(final AssociationRepository associationRepository,
            @Qualifier("imageStorageService") final StorageService imageStorageService,
            final ApplicationContext applicationContext) {
        this.associationRepository = associationRepository;
        this.imageStorageService = imageStorageService;
        this.applicationContext = applicationContext;
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

    @Override
    public List<OrderDetail> getAllNewlyArrivedOrders(final Long associationId) {

        return applicationContext.getBean(OrderService.class)
                .getOrderWithAssociationIdAndAssociationStatus(associationId, false);

    }

    @Override
    public List<OrderDetail> getUnderProcessOrders(final Long associationId) {

        final Association association = this.getAssociation(associationId);

        return applicationContext.getBean(OrderService.class).getUnderProcessOrdersWithAssociationId(association);
    }

    @Override
    public List<OrderDetail> getCompletedOrder(final Long associationId) {

        final Association association = this.getAssociation(associationId);

        List<String> orderDetailStatusList = Arrays.asList(
            OrderDetailStatus.COMPLETED.toString(), OrderDetailStatus.DELIVERY_UNDER_PROCESS.toString(), OrderDetailStatus.DELIVER.toString(), OrderDetailStatus.SENT_TO_DELIVERY_PARTNER.toString(),
           OrderDetailStatus.DELIVERED.toString()
        );

    

        return applicationContext.getBean(OrderService.class).getOrdersWithAssociationAndStatus(association, orderDetailStatusList);
    }

    @Override
    public void acceptOrder(final Long orderId) {

        applicationContext.getBean(OrderService.class).updateHasAssociationAccepted(orderId, true);
    }

    @Override
    public void setOrderCompleted(final Long orderId) {

        final OrderService orderService = applicationContext.getBean(OrderService.class);
        final OrderDetail order = orderService.getOrder(orderId);
        final Employee customerCare = order.getEmployee();

        orderService.changeOrderStatus(orderId, customerCare.getId(), OrderDetailStatus.READY_TO_DELIVER);

    }

    @Override
    public List<OrderDetail> getReadyToDeliverOrders(Long associationId) {

        final Association association = this.getAssociation(associationId);

        return applicationContext.getBean(OrderService.class).getOrdersWithAssociationAndStatus(association,
                OrderDetailStatus.READY_TO_DELIVER);
    }

    @Override
    @Transactional
    public void processForDelivery(Long orderId) {

        final OrderService orderService = applicationContext.getBean(OrderService.class);
        final OrderDetail order = orderService.getOrder(orderId);

        order.setOrderStatus(OrderDetailStatus.SENT_TO_DELIVERY_PARTNER.toString());

    }

}
