package com.albumbazaar.albumbazar.services.impl;

import com.albumbazaar.albumbazar.dao.OrderAndCustomerCareRepository;
import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
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
            orderAndCustomerCareEntity.setOrder(orderService.getOrder(orderAndCustomerCare.getOrderId()));
            orderAndCustomerCareEntity
                    .setCustomerCareEmployee(employeeService.getEmployee(orderAndCustomerCare.getCustomerCareId()));

            orderAndCustomerCareRepository.save(orderAndCustomerCareEntity);

        } catch (Exception e) {
            logger.error(e.getMessage());
            orderAndCustomerCareEntity = null;
        }

        return orderAndCustomerCareEntity;
    }

}