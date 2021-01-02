package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer customerDTOToCustomerEntity(CustomerDTO customerDTO);

    @Mappings({ @Mapping(target = "password", ignore = true) })
    CustomerDTO customerEntityToCustomerDTO(Customer customer);

}
