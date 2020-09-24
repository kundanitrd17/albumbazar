package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;

public interface CustomerService {

    Boolean registerCustomer(final BasicCustomerDetailForm customerDetail, final LocationForm addressDetail);

}
