package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.Customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer customerDTOToCustomerEntity(CustomerDTO customerDTO);

    CustomerDTO customerEntityToCustomerDTO(Customer customer);

}
