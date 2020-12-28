package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;

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
     * Return Customer as output
     * 
     * @return Customer
     */
    Customer getCustomer(Long id);

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

    /**
     * set status to false for the customer
     * 
     * @param id id of the customer
     * @return Customer entity with updated values
     */
    Customer deleteCustomer(Long id);

    /**
     * set status to true for the customer
     * 
     * @param id
     * @return Customer entity with updated values
     */
    Customer restoreCustomer(Long id);

    /**
     * Get a list of orderdetail DTO list
     * 
     * @param customerId id of the customer whose orders needs to be fetched
     * @return List of OrderDetailDTO
     */
    List<OrderDetailDTO> getAllOrderDetails(Long customerId);

    /**
     * Delete an address for a customer
     * 
     * @param customerId id of the customer whose address needs to get delete
     * @param addressId  id of the address which needs to be deleted
     */
    void deleteAddress(long customerId, long addressId);

    /**
     * Updates existing address if address id is provide or else persist a new
     * address entity in the database if id is null
     * 
     * @param addressDTO DTO object of the address entity
     * @param customerId id the customer with whom the address is associated to
     */
    void updateOrAddAddress(AddressDTO addressDTO, Long customerId);

}
