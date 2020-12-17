package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
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
    Customer registerCustomer(final BasicCustomerDetailForm customerDetail, final LocationForm addressDetail);

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

    /**
     * Update customer info
     * 
     * @param customerDTO
     * @return
     */
    Customer updateCustomerInfo(CustomerDTO customerDTO);

    /**
     * Customer's wallet amount
     * 
     * @param id
     * @return Total amount in wallet
     */
    Float getWalletAmount(Long id);

    /**
     * add amount to the existing wallet
     * 
     * @param id     of the customer whose wallet needs to be updated
     * @param amount amount needs to be added
     * @return totol amount
     */
    Float updateWallet(Long id, float amount);

    Float getDiscount(Long id);

    /**
     * It will replace the previous discount to the amount passed
     * 
     * @param id     customer's id
     * @param amount amount it needs to be set to
     * @return current amount
     */

    Float setDiscount(Long id, float amount);

}
