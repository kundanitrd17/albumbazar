package com.albumbazaar.albumbazar.services.impl;

import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("addressService")
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    protected AddressServiceImpl(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressEntity getAddress(final Long addressId) {
        return addressRepository.findById(addressId).orElseThrow();
    }

}
