package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.model.Address1;

public interface LocationService {

    Address1 addNewAddress(final LocationForm addressDetail);

}
