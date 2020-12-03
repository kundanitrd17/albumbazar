package com.albumbazaar.albumbazar.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.SheetDetailRepository;
import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
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

    private OrderRepository orderRepository;
    private SheetDetailRepository sheetDetailRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository, final SheetDetailRepository sheetDetailRepository) {

        this.orderRepository = orderRepository;
        this.sheetDetailRepository = sheetDetailRepository;
    }

    @Override
    @Transactional
    public boolean addOrder(final OrderDetailForm orderDetails) {
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

        orderRepository.save(order);

        return true;
    }

    @Override
    public OrderDetail getOrder(final Long id) throws NoSuchElementException {
        return orderRepository.findById(id).get();
    }

    @Override
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

}
