package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dao.CustomerRepository;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Address1;
import com.albumbazaar.albumbazar.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("customerService")
public class CustomerService {

    private CustomerRepository customerRepository;
    private LocationService locationService;
    @Autowired
    public CustomerService(final CustomerRepository customerRepository,
                           final LocationService locationService) {

        this.locationService = locationService;
        this.customerRepository = customerRepository;
    }

    public Boolean registerCustomer(final BasicCustomerDetailForm customerDetail,
                                    final LocationForm addressDetail) {

        // fill in the location Model
        final Address1 address1 = locationService.addNewAddress(addressDetail);

        final Customer customer = new Customer(customerDetail);
        customer.setAddress(address1);

        customerRepository.save(customer);

        return true;
    }

}
