package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.model.AddressEntity;

public interface AddressService {

    AddressEntity getAddress(Long addressId);

    AddressEntity saveAddress(AddressEntity addressEntity);

    void deleteAddress(AddressEntity addressEntity);

}
