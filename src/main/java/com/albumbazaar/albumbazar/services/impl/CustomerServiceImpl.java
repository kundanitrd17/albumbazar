package com.albumbazaar.albumbazar.services.impl;

import java.util.List;

import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.LocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("customerService")
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;
    private LocationService locationService;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository, final LocationService locationService) {

        this.locationService = locationService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Boolean registerCustomer(final BasicCustomerDetailForm customerDetail, final LocationForm addressDetail) {
        final Customer customer = new Customer(customerDetail);

        try {
            // Save the address using location service and than link it to the customer
            // entity
            customer.setAddress(locationService.addNewAddress(addressDetail));
        } catch (Exception e) {
            logger.info("Unable to save Customer's address." + e.getMessage());
        }

        try {

            customerRepository.save(customer);

            System.out.println(customer.getId());

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to register new customer");
        }

        return true;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getDiscountedCustomer() {

        return customerRepository.findByDiscountGreaterThan(Float.parseFloat("0"));
    }

    @Override
    public List<Customer> getBlockeList() {

        // Find all deactivated customers
        return customerRepository.findByActive(false);
    }

}
