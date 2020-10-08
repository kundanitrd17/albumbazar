package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Customer;

public interface CustomerService {

    /**
     * Register a new customer
     * 
     * @param customerDetail form containing all the customer details
     * @param addressDetail  form containing all the location details
     * @return a boolean value indicating whether the transaction has been completed
     *         or not
     */
    Boolean registerCustomer(final BasicCustomerDetailForm customerDetail, final LocationForm addressDetail);

    /**
     * 
     * @return list of all the customer enrolled
     */
    List<Customer> getAllCustomer();

    /**
     * 
     * @return a list of all discounted customer
     */
    List<Customer> getDiscountedCustomer();

    /**
     * 
     * @return a list of all the blocked customers
     */
    List<Customer> getBlockeList();

}
