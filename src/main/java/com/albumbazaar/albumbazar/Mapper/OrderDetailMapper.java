package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.model.OrderDetail;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail orderDetailDTOTOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO orderDetailToOrderDetailDTO(OrderDetail orderDetail);

}
