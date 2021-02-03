package com.albumbazaar.albumbazar.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.OrderAndCustomerCareRepository;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.CustomerCareEmployeeService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.utilities.OrderAndCustomerCarePool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("customerCareEmployeeService")
public class CustomerCareEmployeeServiceImpl implements CustomerCareEmployeeService {

    private final OrderService orderService;
    private final EmployeeService employeeService;
    private final OrderAndCustomerCareRepository orderAndCustomerCareRepository;

    private Logger logger = LoggerFactory.getLogger(CustomerCareEmployeeServiceImpl.class);

    @Autowired
    protected CustomerCareEmployeeServiceImpl(@Qualifier("orderService") final OrderService orderService,
            @Qualifier("employeeService") final EmployeeService employeeService,
            final OrderAndCustomerCareRepository orderAndCustomerCareRepository) {
        this.employeeService = employeeService;
        this.orderService = orderService;
        this.orderAndCustomerCareRepository = orderAndCustomerCareRepository;
    }

    @Override
    @Transactional
    public OrderAndCustomerCareEntity addOrderOfCustomerCare(final OrderAndCustomerCarePool orderAndCustomerCare) {

        OrderAndCustomerCareEntity orderAndCustomerCareEntity = new OrderAndCustomerCareEntity();
        try {

            // Fetching order and Customer care employee
            final OrderDetail orderInfo = orderService.getOrder(orderAndCustomerCare.getOrderId());
            final Employee customerCareEmployee = employeeService.getEmployee(orderAndCustomerCare.getCustomerCareId());

            // Populate the orderAndCustomerCare entity
            orderAndCustomerCareEntity.setOrder(orderInfo);
            orderAndCustomerCareEntity.setCustomerCareEmployee(customerCareEmployee);

            orderInfo.setOrderStatus(OrderDetailStatus.PROCESSING.toString());
            orderInfo.setEmployee(customerCareEmployee);

            orderAndCustomerCareRepository.save(orderAndCustomerCareEntity);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("unable to save new orderAndCustomerCareEntity");
        }

        return orderAndCustomerCareEntity;
    }

    @Override
    public List<OrderDetail> acceptedOrdersByCustomerCare(final Long customerCareId) {
        if (customerCareId == null) {
            throw new RuntimeException("No such customer care");
        }

        // final Employee customerCare = employeeService.getEmployee(customerCareId);

        return orderAndCustomerCareRepository.findAllWithEmployeeId(customerCareId).stream()
                .map(entity -> entity.getOrder()).collect(Collectors.toList());

    }

    @Override
    public List<OrderDetail> getCompletedOrders(final Long customerCareId) {

        if (customerCareId == null) {
            throw new RuntimeException("invalid Customer care");
        }

        final Employee employee = employeeService.getEmployee(customerCareId);

        final List<OrderDetailStatus> orderStatusList = Arrays.asList(OrderDetailStatus.COMPLETED);

        return orderService.getOrdersAssociatedWithEmployeeAndStatus(employee, orderStatusList);
    }

}
