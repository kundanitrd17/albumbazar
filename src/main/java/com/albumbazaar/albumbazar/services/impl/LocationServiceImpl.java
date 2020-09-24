package com.albumbazaar.albumbazar.services.impl;

import com.albumbazaar.albumbazar.dao.Address1Repository;
import com.albumbazaar.albumbazar.dao.Address2Repository;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.model.Address1;
import com.albumbazaar.albumbazar.model.Address2;
import com.albumbazaar.albumbazar.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("locationService")
public class LocationServiceImpl implements LocationService {

    private final Address1Repository address1Repository;
    private final Address2Repository address2Repository;

    @Autowired
    public LocationServiceImpl(final Address1Repository address1Repository,
            final Address2Repository address2Repository) {
        this.address1Repository = address1Repository;
        this.address2Repository = address2Repository;
    }

    public Address1 addNewAddress(final LocationForm addressDetail) {

        // working on the address2 creation (pin address)
        final Address2 address2 = new Address2(addressDetail); // creating the address2 model
        address2Repository.save(address2); // saving the model

        // Working on the address1 creation (street address)
        final Address1 address1 = new Address1(addressDetail); // creating the address1 model
        address1.setAddress2(address2); // mapping the address2 to address1
        address1Repository.save(address1); // saving address1

        return address1;
    }

}
