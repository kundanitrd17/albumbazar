package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.model.OrderDetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail orderDetailDTOTOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO orderDetailToOrderDetailDTO(OrderDetail orderDetail);

}
