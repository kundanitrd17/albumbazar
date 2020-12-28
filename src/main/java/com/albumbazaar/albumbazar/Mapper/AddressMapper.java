package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity addressDTOToAddressEntity(AddressDTO addressDTO);

    AddressDTO addressToAddressDto(AddressEntity addressEntity);

}
