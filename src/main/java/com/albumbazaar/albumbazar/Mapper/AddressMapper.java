package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressEntity addressDTOToAddressEntity(AddressDTO addressDTO);

    AddressDTO addressToAddressDto(AddressEntity addressEntity);

}
