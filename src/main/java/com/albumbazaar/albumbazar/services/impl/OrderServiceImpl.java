package com.albumbazaar.albumbazar.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.albumbazaar.albumbazar.dao.OrderAndCustomerCareRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.SheetDetailRepository;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.model.SheetDetail;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("orderService")
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final SheetDetailRepository sheetDetailRepository;
    private final OrderAndCustomerCareRepository orderAndCustomerCareRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository, final SheetDetailRepository sheetDetailRepository,
            final OrderAndCustomerCareRepository orderAndCustomerCareRepository) {

        this.orderAndCustomerCareRepository = orderAndCustomerCareRepository;
        this.orderRepository = orderRepository;
        this.sheetDetailRepository = sheetDetailRepository;
    }

    @Override
    @Transactional
    public OrderDetail addOrder(final OrderDetailForm orderDetails) {
        // parse the form to get in proper input

        final OrderDetail order = new OrderDetail(orderDetails); // parse orderdetail to get a proper order

        final List<SheetDetail> sheets = new ArrayList<>(50);

        final String[] paperQuality = orderDetails.getSheetType();
        final String[] paperPrice = orderDetails.getSheetPrice();

        for (int index = 0; index < orderDetails.getSheetType().length; index++) {

            SheetDetail sheet = new SheetDetail();
            sheet.setPaperIndex(index + 1);

            sheetDetailRepository.save(sheet);

            sheets.add(sheet);
        }

        order.setSheets(sheets);

        return orderRepository.save(order);

    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetail getOrder(final Long id) throws NoSuchElementException {
        return orderRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetail> getOrderByPaymentStatus(final Boolean paymentStatus) {
        if (paymentStatus == null) {
            return new ArrayList<OrderDetail>();
        }
        return orderRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<OrderDetail> getAllOrderWithStatus(final String status) {

        try {
            if (status.equalsIgnoreCase("process")) {
                Collection<String> orderStatus = Arrays.asList("pending", "completed").stream()
                        .collect(Collectors.toList());
                return orderRepository.findByOrderStatusNotIn(orderStatus);
            }
            return orderRepository.findByOrderStatus(status);
        } catch (Exception e) {
            System.out.println("order service" + e);
        }
        return null;
    }

    @Override
    public List<OrderDetail> getOrdersWithStatus(final OrderDetailStatus status) {
        try {
            return orderRepository.findByOrderStatus(status.toString());
        } catch (Exception e) {
            System.out.println("Unable to fetch orders from the pool");
        }
        return null;
    }

    @Override
    @Transactional
    public OrderDetail updateOrderInfo(final Long id, final OrderDetailDTO orderDetailDTO) {

        final OrderDetail orderDetailEntity = orderRepository.findById(id).orElseThrow();

        if (orderDetailDTO.getAssociationName() != null && !orderDetailDTO.getAssociationName().isBlank()) {
            orderDetailEntity.setAssociationName(orderDetailDTO.getAssociationName().trim());
        }

        if (orderDetailDTO.getDescription() != null && !orderDetailDTO.getDescription().isBlank()) {
            orderDetailEntity.setDescription(orderDetailDTO.getDescription().trim());
        }

        if (orderDetailDTO.getNoOfSheets() != null) {
            orderDetailEntity.setNoOfSheets(orderDetailDTO.getNoOfSheets());
        }

        if (orderDetailDTO.getOrientation() != null && !orderDetailDTO.getOrientation().isBlank()) {
            orderDetailEntity.setOrientation(orderDetailDTO.getOrientation());
        }

        if (orderDetailDTO.getProductName() != null && !orderDetailDTO.getProductName().isBlank()) {
            orderDetailEntity.setProductName(orderDetailDTO.getProductName());
        }

        return orderDetailEntity;
    }

    /**
     * Change order status to orderStatus
     */
    @Override
    @Transactional
    public void changeOrderStatus(Long orderId, Long customerCareId, OrderDetailStatus orderStatus) {

        // Change the status of the order detail

        final OrderAndCustomerCareEntity orderAndCustomerCareEntity = orderAndCustomerCareRepository
                .findByCustomerAndOrder(orderId, customerCareId).orElseThrow();
        orderAndCustomerCareEntity.getOrder().setOrderStatus(orderStatus.toString());

        // If the order is completed
        if (orderStatus.equals(OrderDetailStatus.COMPLETED) || orderStatus.equals(OrderDetailStatus.PENDING)) {
            orderAndCustomerCareRepository.delete(orderAndCustomerCareEntity);

        }

    }

    @Override
    public List<OrderDetail> getOrdersAssociatedWithEmployeeAndStatus(final Long employeeId,
            final OrderDetailStatus orderDetailStatus) {

        return orderRepository.findAllByEmployeeId(employeeId, orderDetailStatus.toString());

    }

    @Override
    public List<OrderDetailStatus> availableOrderStatus() {
        return Stream.of(OrderDetailStatus.values()).collect(Collectors.toList());
    }

    @Override
    public OrderDetail getOrderWithRazorpayOrderId(String razorpayOrderId) {

        return orderRepository.findByRazorpayOrderId(razorpayOrderId).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrdersOfCustomer(final Long customerId) {

        return orderRepository.findAllByCustomerId(customerId).orElseThrow();
    }

}
